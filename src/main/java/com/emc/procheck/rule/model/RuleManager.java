/**
 * 
 */
package com.emc.procheck.rule.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emc.procheck.config.CommonHealthCheckConfig;
import com.emc.procheck.postgres.multitenancy.TenantService;
import com.emc.procheck.rule.service.RuleResultService;
import com.emc.procheck.storage.model.Event;
import com.emc.procheck.storage.service.EventService;

/**
 * @author Eric Wu
 *
 */
@Component
public class RuleManager {

	private final static Logger logger = LoggerFactory.getLogger(RuleManager.class);

	@Autowired
	private RuleFactory ruleFactory;

	@Autowired
	private CommonHealthCheckConfig hcConfig;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private EventService eventService;
	
	@Autowired
	private RuleResultService ruleResultService;
	
	
	private final String HEALTH_SCORE_FILE = "/health_tree.json";

	
	public RuleManager() {
	}

	public List<IRule> getRules() {
		return ruleFactory.getRules();
	}

    public List<RuleResult> runRulesBySn(String sn) {
        String systemKey = null;
        Event event = eventService.findLatestBySerialNumber(sn);
        if (event != null)
            systemKey = event.getSystemKey();
        logger.debug("Got the latest systemKey[" + systemKey + "] by sn[" + sn + "]");
        
		List<RuleResult> results = runRulesBySystemKey(systemKey);
		StorageComponent comp = constructHealthComponent(sn);
		event.setScore(comp.getScore());
		eventService.save(event);
		
		return results;
    }

	public List<RuleResult> runRulesBySystemKey(String systemKey) {
		if (systemKey == null) {
			logger.error("Ignored healthcheck because systemKey is null.");
			return null;
		}
		
		String tenant = tenantService.getCurrentTenant();

		List<IRule> rules = ruleFactory.getRules();

		ExecutorService executorService = Executors.newCachedThreadPool();
		for (IRule rule : rules) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					tenantService.setCurrentTenant(tenant);
					rule.check(systemKey);
				}
			});
		}

		try {
			logger.info("Wait all task completion and shutdown executor.");
			executorService.shutdown();
			executorService.awaitTermination(hcConfig.getRuleTimeout(), TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.error("Tasks interrupted", e);
		} finally {
			if (!executorService.isTerminated()) {
				logger.error("Cancel non-finished tasks");
				executorService.shutdownNow();
			}
			logger.info("Shutdown finished");
		}

		// Persist rule result
		List<RuleResult> results = new ArrayList<RuleResult>();
		for (IRule rule : rules) {
			ruleResultService.save(rule.getResult());
			results.add(rule.getResult());
		}
		
		return results;
	}
	
	public StorageComponent constructHealthComponent(String serialNumber) {

		StorageComponent comp = loadHealthComponent();
		loadHealthCheckResult(comp, serialNumber);
	
		return comp;
	}

	public StorageComponent loadHealthComponent() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		StorageComponent comp = null;

		URL htConfig = getClass().getResource(HEALTH_SCORE_FILE);
		if (htConfig == null) {
			logger.error("Cannot load health tree " + HEALTH_SCORE_FILE);
		}

		File htFile;
		try {
			htFile = new File(htConfig.toURI());
			InputStream htStream = new FileInputStream(htFile);
			comp = objectMapper.readValue(htStream, StorageComponent.class);
		} catch (Exception e) {
			logger.error("Failed to load health tree config.", e);
		}
		
		return comp;
	}
	
	private void loadHealthCheckResult(StorageComponent component, String serialNumber) {
		if (component.getChildren() == null) {
			loadRuleResult(component, serialNumber);
			return;
		}
		
		double score = 0;
		for(StorageComponent child : component.getChildren()) {
			loadHealthCheckResult(child, serialNumber);
			score += child.getScore() * child.getWeight();
			if (child.getActions() != null) {
			    component.addActions(child.getActions());
			}
		}
		
		component.setScore((int) score);
	}

	private void loadRuleResult(StorageComponent component, String serialNumber) {
		// We only have rules for Software now, use default value in json file
		if ( !"Software".equals(component.getName()) && !"Disk".equals(component.getName()) ) {
			return;
		}
		
		List<RuleResult> results = ruleResultService.findLatestRuleResults(component.getName(), serialNumber);
		double score = 0;
		double totalWeight = 0;
		for (RuleResult result : results) {
			score += result.getScore() * result.getWeight();
			totalWeight += result.getWeight();
			if (result.getActions() != null) {
				component.addActions(result.getActions());
			}
		}
		
		/* 
		 * In case the summarized rule weight is not 1.0 considering
		 *  - The rules are selectable so we would only have a sub-set of rules
		 *  - New rules could be added without affecting weight of exising rules
		 */
		score /= totalWeight;
		
		component.setScore((int)score);
	}
}

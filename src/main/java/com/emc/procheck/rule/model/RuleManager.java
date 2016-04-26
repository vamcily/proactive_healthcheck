/**
 * 
 */
package com.emc.procheck.rule.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emc.procheck.config.CommonHealthCheckConfig;
import com.emc.procheck.postgres.multitenancy.TenantService;
import com.emc.procheck.rule.model.RuleResult.RuleStatus;
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

	public RuleManager() {
	}

	public List<IRule> getRules() {
		return ruleFactory.getRules();
	}

    public HealthCheckResult runRulesBySn(String sn) {
        String systemKey = null;
        Event event = eventService.findLatestBySerialNumber(sn);
        if (event != null)
            systemKey = event.getSystemKey();
        logger.debug("Got the latest systemKey[" + systemKey + "] by sn[" + sn + "]");
        return runRulesBySystemKey(systemKey);
    }

	public HealthCheckResult runRulesBySystemKey(String systemKey) {
		if (systemKey == null) {
			logger.error("Ignored healthcheck because systemKey is null.");
			return null;
		}
		
		String tenant = tenantService.getCurrentTenant();

		List<IRule> rules = ruleFactory.getRules();
		HealthCheckResult checkResult = new HealthCheckResult();

		String id = systemKey + "_" + System.currentTimeMillis();
		checkResult.setId(id);
		checkResult.setStartTime(new Date());

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

		StringBuilder timeoutRules = new StringBuilder();
		for (IRule rule : rules) {
			RuleStatus status = rule.getResult().getStatus();
			if (status == RuleStatus.NOT_RUN || status == RuleStatus.RUNNING) {
				timeoutRules.append(rule.getName()).append(" ");
			}
		}
		if (timeoutRules.length() > 0)
			logger.error("The following rules were not completed: " + timeoutRules.toString());

		checkResult.setEndTime(new Date());
		checkResult.setRules(rules);

		return checkResult;
	}
}

/**
 * 
 */
package com.emc.procheck.rule.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.rule.model.IRule;
import com.emc.procheck.rule.model.RuleManager;
import com.emc.procheck.rule.model.RuleResult;
import com.emc.procheck.storage.model.UemSystem;
import com.emc.procheck.storage.service.UemSystemService;

@Service(value = "healthCheckService")
public class HealthCheckServiceImpl implements HealthCheckService {

    @Autowired
    private RuleManager ruleManager;
    
    @Autowired
    private UemSystemService uemSystemService;

    @Override
    public List<RuleResult> runCheckBySystemKey(String systemKey) {
        return ruleManager.runRulesBySystemKey(systemKey);
    }
    
    @Override
    public List<RuleResult> runCheckBySn(String sn) {
        return ruleManager.runRulesBySn(sn);
    }
    
	@Override
	public List<RuleResult> runRules() {
		List<UemSystem> systems = uemSystemService.findAll();
		// TODO We have 3000+ systems, just run rules on some of them to save time
		int total = 10;
		List<RuleResult> results = new ArrayList<RuleResult>();
		for (UemSystem system : systems) {
			results.addAll(ruleManager.runRulesBySn(system.getSerialNumber()));
			total -= 1;
			if (total == 0) {
				break;
			}
		}
		
		return results;
	}

	@Override
	public List<IRule> getRules() {
		return ruleManager.getRules();
	}
    
    
}

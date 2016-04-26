/**
 * 
 */
package com.emc.procheck.rule.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.rule.model.HealthCheckResult;
import com.emc.procheck.rule.model.IRule;
import com.emc.procheck.rule.model.RuleManager;

@Service(value = "healthCheckService")
public class HealthCheckServiceImpl implements HealthCheckService {

    @Autowired
    private RuleManager ruleManager;

    @Override
    public HealthCheckResult runCheckBySystemKey(String systemKey) {
        return ruleManager.runRulesBySystemKey(systemKey);
    }
    
    @Override
    public HealthCheckResult runCheckBySn(String sn) {
        return ruleManager.runRulesBySn(sn);
    }

	@Override
	public List<IRule> getRules() {
		return ruleManager.getRules();
	}
    
    
}

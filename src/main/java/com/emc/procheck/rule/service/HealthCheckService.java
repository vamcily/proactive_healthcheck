/**
 * 
 */
package com.emc.procheck.rule.service;


import java.util.List;

import com.emc.procheck.rule.model.IRule;
import com.emc.procheck.rule.model.RuleResult;

public interface HealthCheckService {

	List<RuleResult> runCheckBySn(String sn);
	List<RuleResult> runCheckBySystemKey(String systemKey);
    
	List<RuleResult> runRules();
    List<IRule> getRules();
}

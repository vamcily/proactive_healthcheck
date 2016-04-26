/**
 * 
 */
package com.emc.procheck.rule.service;


import java.util.List;

import com.emc.procheck.rule.model.HealthCheckResult;
import com.emc.procheck.rule.model.IRule;

public interface HealthCheckService {

	HealthCheckResult runCheckBySn(String sn);
    HealthCheckResult runCheckBySystemKey(String systemKey);
    
    List<IRule> getRules();
}

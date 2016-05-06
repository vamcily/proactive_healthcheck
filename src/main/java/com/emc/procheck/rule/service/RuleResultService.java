package com.emc.procheck.rule.service;

import java.util.List;

import com.emc.procheck.rule.model.RuleResult;
import com.emc.procheck.rule.model.StorageComponent;

public interface RuleResultService {

	public RuleResult findByID(String id);
    
    public List<RuleResult> findAll();
    
    public void save(RuleResult object);
    
    public void deleteByID(String id);

    public void deleteAll();
    
    public List<RuleResult> findLatestRuleResults(String component, String serialNumber);
    
    public StorageComponent retrieveHealthResult(String serialNumber);

}

package com.emc.procheck.rule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emc.procheck.rule.dao.RuleResultRepository;
import com.emc.procheck.rule.model.RuleManager;
import com.emc.procheck.rule.model.RuleResult;
import com.emc.procheck.rule.model.StorageComponent;

@Service
public class RuleResultServiceImpl implements RuleResultService {
	
    protected RuleResultRepository repository;
    
    @Autowired
    private RuleManager ruleMgr; 
    
    @Autowired
    public RuleResultServiceImpl(RuleResultRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public RuleResult findByID(String id) {
        return repository.findOne(id);
    }
    
    @Override
    public List<RuleResult> findAll() {
    	return repository.findAll();
    }

    @Override
    @Transactional
    public void save(RuleResult object) {
        repository.save(object);
    }
    
    @Override
    @Transactional
    public void deleteByID(String id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
    
	@Override
	public List<RuleResult> findLatestRuleResults(String component, String serialNumber) {
		List<RuleResult> allResults = repository.findByComponentAndSerialNumberOrderByStartTimeDesc(component, serialNumber);
		List<RuleResult> latestResults = new ArrayList<RuleResult>();
		Set<String> rules = new TreeSet<String>();
		for (RuleResult result : allResults) {
			if (rules.contains(result.getRuleId())) {
				continue;
			}
			
			rules.add(result.getRuleId());
			latestResults.add(result);
		}
		return latestResults;
	}

	@Override
	public StorageComponent retrieveHealthResult(String serialNumber) {
		return ruleMgr.constructHealthComponent(serialNumber);
	}

	@Override
	public void fixComponent(String serialNumber, String component) {
		List<RuleResult> results = findLatestRuleResults(component, serialNumber);
		for (RuleResult rst : results) {
			rst.setScore(100);
			List<String> acts = new ArrayList<String>();
			acts.add("Fixed");
			rst.setActions(acts);
		}
		repository.save(results);
	}
}

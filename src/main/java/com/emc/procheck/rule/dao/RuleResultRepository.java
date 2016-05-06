package com.emc.procheck.rule.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emc.procheck.rule.model.RuleResult;

/**
 * Repository to access transform models.
 */
public interface RuleResultRepository extends JpaRepository<RuleResult, String>{
	
	List<RuleResult> findByComponentAndSerialNumberOrderByStartTimeDesc(String component, String serialNumber);

}

package com.emc.procheck.storage.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emc.procheck.storage.model.RuleInfo;

/**
 * Repository to access transform models.
 */
public interface RuleInfoRepository extends TransformRepository<RuleInfo, String>{
	
    //public RuleInfo findOneById(String id);
    
    //List<RuleInfo> findById(String id);    
}

package com.emc.procheck.storage.dao;



import java.util.List;

import com.emc.procheck.storage.model.UemSystem;

/**
 * Repository to access transform models.
 */
public interface UemSystemRepository extends TransformRepository<UemSystem, String>{

	List<UemSystem> findByVersion(String version);
	
	UemSystem findBySystemKey(String systemKey);
}

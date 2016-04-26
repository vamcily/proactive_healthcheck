package com.emc.procheck.storage.dao;


import java.util.List;

import com.emc.procheck.storage.model.UemRaidGroup;


public interface UemRaidGroupRepository extends TransformRepository<UemRaidGroup, String>{

	public List<UemRaidGroup> findBySystemKey(String systemKey);

	public List<UemRaidGroup> findBySystemKeyAndRaidType(String systemKey, String raidType);
    
}

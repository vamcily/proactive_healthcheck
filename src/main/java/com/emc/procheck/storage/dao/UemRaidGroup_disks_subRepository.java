package com.emc.procheck.storage.dao;


import java.util.List;

import com.emc.procheck.storage.model.UemRaidGroup_disks_sub;


public interface UemRaidGroup_disks_subRepository extends TransformRepository<UemRaidGroup_disks_sub, String>{

	public List<UemRaidGroup_disks_sub> findByDisksKey(String disksKey);
    
}

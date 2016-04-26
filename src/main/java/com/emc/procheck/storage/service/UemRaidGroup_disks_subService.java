package com.emc.procheck.storage.service;


import java.util.List;

import com.emc.procheck.storage.model.UemRaidGroup_disks_sub;


public interface UemRaidGroup_disks_subService extends TransformService<UemRaidGroup_disks_sub, String> {

	public final static String BEANNAME = "uemRaidGroup_disks_subServiceTransform";

	public List<UemRaidGroup_disks_sub> findByDisksKey(String disksKey);

}

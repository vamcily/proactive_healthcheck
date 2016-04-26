package com.emc.procheck.storage.service;


import java.util.List;

import com.emc.procheck.storage.model.UemRaidGroup;


public interface UemRaidGroupService extends TransformService<UemRaidGroup, String> {

	public final static String BEANNAME = "uemRaidGroupServiceTransform";

	public List<UemRaidGroup> findBySystemKey(String systemKey);
	public List<UemRaidGroup> findBySystemKeyAndRaidType(String systemKey, String raidType);

}

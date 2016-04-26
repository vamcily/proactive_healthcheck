package com.emc.procheck.storage.service;


import java.util.List;

import com.emc.procheck.storage.model.UemDiskGroup;


public interface UemDiskGroupService extends TransformService<UemDiskGroup, String> {

	public final static String BEANNAME = "uemDiskGroupServiceTransform";

	public List<UemDiskGroup> findBySystemKey(String systemKey);

}

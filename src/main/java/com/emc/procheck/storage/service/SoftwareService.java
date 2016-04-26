package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.Software;

public interface SoftwareService extends TransformService<Software, Long> {

	public final static String BEANNAME = "softwareerviceTransform";
	
	public List<Software> findBySystemKey(String systemKey);

}

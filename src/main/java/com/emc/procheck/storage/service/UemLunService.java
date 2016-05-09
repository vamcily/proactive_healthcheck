package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.UemLun;


public interface UemLunService extends TransformService<UemLun, String> {
	 public final static String BEANNAME = "uemLunServiceTransform";
	    
	 public UemLun findByLunKey(String lunKey);
	    
	 public List<UemLun> findBySystemKey(String systemKey);
}

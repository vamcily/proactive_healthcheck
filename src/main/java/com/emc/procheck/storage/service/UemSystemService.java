package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.UemSystem;


public interface UemSystemService extends TransformService<UemSystem, String> {

	public final static String BEANNAME = "uemSystemService";
	
	public List<UemSystem> findAll();
	
	public List<UemSystem> findByVersion(String version);

}

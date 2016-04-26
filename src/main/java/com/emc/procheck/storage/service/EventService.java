package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.Event;


public interface EventService extends TransformService<Event, String> {

	public final static String BEANNAME = "eventServiceTransform";
	
	public List<Event> findAll();
	
	public Event findLatestBySerialNumber(String serialNumber);
	
	public Event findBySystemKey(String systemKey);
	
	public List<Event> findBySoftwareVersion(String version);

}

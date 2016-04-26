package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.Inventory;

public interface InventoryService extends TransformService<Inventory, String> {

	public final static String BEANNAME = "inventoryServiceTransform";
	
	public List<Inventory> findAllByHardwareType(String systemKey, String hardwareType);
	
	public Long countBySystemAndHardwareType(String systemKey, String hardwareType);
	
	public Long countByParentAndHardwareType(String parentKey, String hardwareType);
	
	public List<Inventory> findDiskByRaidGroupDisksKey(String raidGroupDisksKey);
	
	public Long countBySystemAndHardwareType(String systemKey, String hardwareType, Boolean needsReplacement);

}

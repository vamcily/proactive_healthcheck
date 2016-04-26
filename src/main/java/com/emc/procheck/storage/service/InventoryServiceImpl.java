package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.InventoryRepository;
import com.emc.procheck.storage.model.Inventory;

@Service(value="inventoryServiceTransform")
public class InventoryServiceImpl extends TransformServiceImpl<Inventory, String> implements InventoryService {

    @Autowired
    public InventoryServiceImpl(InventoryRepository repository) {
        super(repository);
    }
    
    private InventoryRepository getRepository(){
    	return (InventoryRepository)repository;
    }

    @Override
    public List<Inventory> findAllByHardwareType(String systemKey, String hardwareType){
        return getRepository().findBySystemKeyAndNickname(systemKey, hardwareType);
    }

    @Override
    public Long countBySystemAndHardwareType(String systemKey, String hardwareType) {
        return getRepository().countBySystemKeyAndNickname(systemKey, hardwareType);
    }

    @Override
    public Long countByParentAndHardwareType(String parentKey, String hardwareType) {
        return getRepository().countByParentKeyAndNickname(parentKey, hardwareType);
    }
    
    @Override
    public List<Inventory> findDiskByRaidGroupDisksKey(String raidGroupDisksKey){
    	return getRepository().findDiskByRaidGroupDisksKey(raidGroupDisksKey);
    }

	@Override
	public Long countBySystemAndHardwareType(String systemKey, String hardwareType, Boolean needsReplacement) {
		return getRepository().countBySystemAndHardwareType(systemKey, hardwareType, needsReplacement);
	}
    
}

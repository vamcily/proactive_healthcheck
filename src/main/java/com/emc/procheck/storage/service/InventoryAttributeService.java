package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.InventoryAttribute;

public interface InventoryAttributeService extends TransformService<InventoryAttribute, Long> {
    public final static String BEANNAME = "inventoryAttributeServiceTransform";
    
    public List<InventoryAttribute> findByInventoryKey(String inventoryKey);
}

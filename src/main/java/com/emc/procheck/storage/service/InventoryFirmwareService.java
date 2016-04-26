package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.InventoryFirmware;

public interface InventoryFirmwareService extends TransformService<InventoryFirmware, Long> {
    public final static String BEANNAME = "inventoryFirmwareServiceTransform";
    
    public List<InventoryFirmware> findByInventoryKey(String inventoryKey);
}

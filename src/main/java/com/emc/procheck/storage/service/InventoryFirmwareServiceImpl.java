package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.InventoryFirmwareRepository;
import com.emc.procheck.storage.model.InventoryFirmware;

@Service(value="inventoryFirmwareServiceTransform")
public class InventoryFirmwareServiceImpl extends TransformServiceImpl<InventoryFirmware, Long> implements InventoryFirmwareService {

    @Autowired
    public InventoryFirmwareServiceImpl(InventoryFirmwareRepository repository) {
        super(repository);
    }

    @Override
    public List<InventoryFirmware> findByInventoryKey(String inventoryKey) {
        return ((InventoryFirmwareRepository)this.repository).findByInventoryKey(inventoryKey);
    }
    
    

}

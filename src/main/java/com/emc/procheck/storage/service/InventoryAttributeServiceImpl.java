package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.InventoryAttributeRepository;
import com.emc.procheck.storage.model.InventoryAttribute;

@Service(value="inventoryAttributeServiceTransform")
public class InventoryAttributeServiceImpl extends TransformServiceImpl<InventoryAttribute, Long> implements InventoryAttributeService {

    @Autowired
    public InventoryAttributeServiceImpl(InventoryAttributeRepository repository) {
        super(repository);
    }
    
    @Override
    public List<InventoryAttribute> findByInventoryKey(String inventoryKey) {
        return ((InventoryAttributeRepository)this.repository).findByInventoryKey(inventoryKey);
    }
}

package com.emc.procheck.storage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emc.procheck.storage.model.InventoryAttribute;

public interface InventoryAttributeRepository extends TransformRepository<InventoryAttribute, Long> {
    @Query("select u from uem_inventory_attributes_ent u where u.inventoryKey = :invenkey")
    public List<InventoryAttribute> findByInventoryKey(@Param("invenkey") String inventoryKey);
}

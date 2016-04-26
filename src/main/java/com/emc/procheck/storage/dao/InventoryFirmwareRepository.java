package com.emc.procheck.storage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emc.procheck.storage.model.InventoryFirmware;

public interface InventoryFirmwareRepository extends TransformRepository<InventoryFirmware, Long> {
    @Query("select u from uem_inventory_firmware_ent u where u.inventoryKey = :invenkey")
    public List<InventoryFirmware> findByInventoryKey(@Param("invenkey") String inventoryKey);
}

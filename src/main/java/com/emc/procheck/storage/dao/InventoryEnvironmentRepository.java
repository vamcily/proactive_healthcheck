package com.emc.procheck.storage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emc.procheck.storage.model.InventoryEnvironment;

public interface InventoryEnvironmentRepository extends TransformRepository<InventoryEnvironment, String> {
    @Query("select u from uem_environment_ent u where u.inventoryKey = :inventoryKey")
    public List<InventoryEnvironment> findByInventoryKey(@Param("inventoryKey") String inventoryKey);
    
    @Query("select u from uem_environment_ent as u, uem_inventory_ent as i where i.systemKey = :systemKey and (i.nickname = 'dpe' or i.nickname = 'dae') and u.inventoryKey = i.inventoryKey")
    public List<InventoryEnvironment> findTempBySystemKey(@Param("systemKey") String systemKey);
    
    @Query("select e from uem_environment_ent as e, uem_inventory_ent as i where i.systemKey = :systemKey and i.nickname = :inventoryType and e.inventoryKey = i.inventoryKey")
    public List<InventoryEnvironment> findInventoryEnvironment(@Param("systemKey") String systemKey, @Param("inventoryType") String inventoryType);
}

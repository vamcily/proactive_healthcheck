package com.emc.procheck.storage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.emc.procheck.storage.model.Inventory;

public interface InventoryRepository extends TransformRepository<Inventory, String>{

    List<Inventory> findBySystemKeyAndNickname(String systemKey, String nickname);
    
    Long countBySystemKeyAndNickname(String systemKey, String nickname);
    
    Long countByParentKeyAndNickname(String parentKey, String nickname);
    
    @Query("select inv from uem_inventory_ent as inv, uem_raidgroup_disks_sub_ent as disks where inv.inventoryKey = disks.diskKey and disks.disksKey = ?1")
    List<Inventory> findDiskByRaidGroupDisksKey(String raidGroupDisksKey);
    
    @Query("select count(inv) from uem_inventory_ent as inv where inv.systemKey = ?1 and inv.nickname = ?2 and inv.needsReplacement = ?3")
    Long countBySystemAndHardwareType(String systemKey, String nickname, Boolean needsReplacement);
}

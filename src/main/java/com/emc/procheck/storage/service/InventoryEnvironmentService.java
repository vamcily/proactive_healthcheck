package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.InventoryEnvironment;

public interface InventoryEnvironmentService extends TransformService<InventoryEnvironment, String> {
    public final static String BEANNAME = "inventoryEnvironmentServiceTransform";
    
    public List<InventoryEnvironment> findByInventoryKey(String inventoryKey);
    
    /**
     * Find inventoryEnvironment on temperature by systemKey.
     * @param systemKey
     * @return
     */
    public List<InventoryEnvironment> findTempBySystemKey(String systemKey);
    
    /**
     * Get average temperature of a system by systemKey. Calculate average values of all dae/dpe.
     * @param systemKey
     * @return
     */
    public Integer findAvgTempBySystemKey(String systemKey);
    
    /**
     * Find specific type of InventoryEnvironment by systemKey
     * @param systemKey
     * @param inventoryType
     * @return
     */
    public List<InventoryEnvironment> findInventoryEnvironment(String systemKey, String inventoryType);
}

package com.emc.procheck.storage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emc.procheck.storage.model.UemPool;

public interface UemPoolRepository extends TransformRepository<UemPool, String> {

    @Query("select u from uem_pool_ent u where u.poolKey = :poolKey")
    public UemPool findByPoolKey(@Param("poolKey") String poolKey);
    
    @Query("select u from uem_pool_ent u where u.systemKey = :systemKey")
    public List<UemPool> findBySystemKey(@Param("systemKey") String systemKey);
}

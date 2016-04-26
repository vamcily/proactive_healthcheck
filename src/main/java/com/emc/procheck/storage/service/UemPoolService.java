package com.emc.procheck.storage.service;

import java.util.List;

import com.emc.procheck.storage.model.UemPool;

public interface UemPoolService extends TransformService<UemPool, String> {

    public final static String BEANNAME = "uemPoolServiceTransform";
    
    public UemPool findByPoolKey(String poolKey);
    
    public List<UemPool> findBySystemKey(String systemKey);
}

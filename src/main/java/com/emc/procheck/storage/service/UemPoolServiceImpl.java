package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.UemPoolRepository;
import com.emc.procheck.storage.model.UemPool;

/**
 * Class to access UemPool transform model table
 *
 */
@Service(value="uemPoolServiceTransform")
public class UemPoolServiceImpl extends TransformServiceImpl<UemPool, String> implements UemPoolService {

    @Autowired
    public UemPoolServiceImpl(UemPoolRepository repository) {
        super(repository);
    }
    
    private UemPoolRepository getRepository() {
        return (UemPoolRepository)this.repository;
    }
    
    @Override
    public UemPool findByPoolKey(String poolKey) {
        return getRepository().findByPoolKey(poolKey);
    }
    
    @Override
    public List<UemPool> findBySystemKey(String systemKey) {
        return getRepository().findBySystemKey(systemKey);
    }
}

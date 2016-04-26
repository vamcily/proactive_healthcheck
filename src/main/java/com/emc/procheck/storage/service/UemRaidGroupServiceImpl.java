package com.emc.procheck.storage.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.UemRaidGroupRepository;
import com.emc.procheck.storage.model.UemRaidGroup;


@Service(value="uemRaidGroupServiceTransform")
public class UemRaidGroupServiceImpl extends TransformServiceImpl<UemRaidGroup, String> implements UemRaidGroupService {

    @Autowired
    public UemRaidGroupServiceImpl(UemRaidGroupRepository repository) {
        super(repository);
    }
    private UemRaidGroupRepository getRepository(){
    	return (UemRaidGroupRepository)this.repository;
    }
    
    @Override 
    public List<UemRaidGroup> findBySystemKey(String systemKey){
    	return getRepository().findBySystemKey(systemKey);
    }
    
    @Override 
    public List<UemRaidGroup> findBySystemKeyAndRaidType(String systemKey, String raidType){
    	return getRepository().findBySystemKeyAndRaidType(systemKey, raidType);
    }
    
}

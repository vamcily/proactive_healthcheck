package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.SoftwareRepository;
import com.emc.procheck.storage.model.Software;

@Service(value="softwareServiceTransform")
public class SoftwareServiceImpl extends TransformServiceImpl<Software, Long> implements SoftwareService {

    @Autowired
    public SoftwareServiceImpl(SoftwareRepository repository) {
        super(repository);
    }
    
    private SoftwareRepository getRepository(){
    	return (SoftwareRepository)this.repository;
    }

	@Override
	public List<Software> findBySystemKey(String systemKey) {
		return getRepository().findBySystemKey(systemKey);
	}

}

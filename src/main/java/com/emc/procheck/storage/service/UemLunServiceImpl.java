package com.emc.procheck.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.UemLunRepository;
import com.emc.procheck.storage.model.UemLun;
import java.util.List;

@Service(value="uemLunServiceTransform")
public class UemLunServiceImpl extends TransformServiceImpl<UemLun, String> implements UemLunService{
	@Autowired
	public 	UemLunServiceImpl(UemLunRepository repository) {
		super(repository);
	}
	
	private UemLunRepository getRepository() {
		return (UemLunRepository)this.repository;
	}
	
	@Override
	public UemLun findByLunKey(String lunKey) {
		return getRepository().findByLunKey(lunKey);
	}
	
	@Override
	public List<UemLun> findBySystemKey(String systemKey) {
		return getRepository().findBySystemKey(systemKey);
	}
}

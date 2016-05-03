package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.UemSystemRepository;
import com.emc.procheck.storage.model.UemSystem;

@Service(value="uemSystemService")
public class UemSystemServiceImpl extends TransformServiceImpl<UemSystem, String> implements UemSystemService {

	@Autowired
	public UemSystemServiceImpl(UemSystemRepository repository) {
		super(repository);
	}

	@Override
	public List<UemSystem> findAll() {
		return ((UemSystemRepository)repository).findAll();
	}

	@Override
	public List<UemSystem> findByVersion(String version) {
		return  ((UemSystemRepository)repository).findByVersion(version);
	}

}

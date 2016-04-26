package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.UemDiskGroupRepository;
import com.emc.procheck.storage.model.UemDiskGroup;

@Service(value = "uemDiskGroupServiceTransform")
public class UemDiskGroupServiceImpl extends TransformServiceImpl<UemDiskGroup, String> implements UemDiskGroupService {

	@Autowired
	public UemDiskGroupServiceImpl(UemDiskGroupRepository repository) {
		super(repository);
	}

	private UemDiskGroupRepository getRepository() {
		return (UemDiskGroupRepository) this.repository;
	}

	@Override
	public List<UemDiskGroup> findBySystemKey(String systemKey) {
		return getRepository().findBySystemKey(systemKey);
	}

}

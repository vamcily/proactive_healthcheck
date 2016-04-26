package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.UemRaidGroup_disks_subRepository;
import com.emc.procheck.storage.model.UemRaidGroup_disks_sub;

@Service(value = "uemRaidGroup_disks_subServiceTransform")
public class UemRaidGroup_disks_subServiceImpl extends TransformServiceImpl<UemRaidGroup_disks_sub, String>
		implements UemRaidGroup_disks_subService {

	@Autowired
	public UemRaidGroup_disks_subServiceImpl(UemRaidGroup_disks_subRepository repository) {
		super(repository);
	}

	private UemRaidGroup_disks_subRepository getRepository() {
		return (UemRaidGroup_disks_subRepository) this.repository;
	}

	@Override
	public List<UemRaidGroup_disks_sub> findByDisksKey(String disksKey) {
		return getRepository().findByDisksKey(disksKey);
	}

}

package com.emc.procheck.storage.dao;

import java.util.List;

import com.emc.procheck.storage.model.UemDiskGroup;

public interface UemDiskGroupRepository extends TransformRepository<UemDiskGroup, String> {

	public List<UemDiskGroup> findBySystemKey(String systemKey);

}

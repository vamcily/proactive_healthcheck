package com.emc.procheck.storage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emc.procheck.storage.model.Software;

public interface SoftwareRepository extends TransformRepository<Software, Long>{

	@Query("select u from uem_software_ent u where u.systemKey = :systemkey")
    public List<Software> findBySystemKey(@Param("systemkey") String systemKey);
}

package com.emc.procheck.storage.dao;

import com.emc.procheck.storage.model.UemLun;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UemLunRepository extends TransformRepository<UemLun, String>{
	   @Query("select u from uem_lun_ent u where u.lunKey = :lunKey")
	   public UemLun findByLunKey(@Param("lunKey") String lunKey);
	   
	   @Query("select u from uem_lun_ent u where u.systemKey = :systemKey")
	   public List<UemLun> findBySystemKey(@Param("systemKey") String systemKey);
}

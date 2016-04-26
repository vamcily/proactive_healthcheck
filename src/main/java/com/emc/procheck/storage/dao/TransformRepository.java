package com.emc.procheck.storage.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Parent repository to access models using id. General query methods are defined here.
 *
 * @param <T>
 */
@NoRepositoryBean
public interface TransformRepository<T, X extends Serializable> extends JpaRepository<T, X>{


}

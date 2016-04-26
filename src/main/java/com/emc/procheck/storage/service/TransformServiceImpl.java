package com.emc.procheck.storage.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.emc.procheck.storage.dao.TransformRepository;

/**
 * Parent class used to access model tables. It contains common methods because all cc models have several general fields.
 *
 * @param <T>
 */
public class TransformServiceImpl<T, X extends Serializable> implements TransformService<T, X> {
    
    protected TransformRepository<T, X> repository;

    public TransformServiceImpl(TransformRepository<T, X> repository) {
        this.repository = repository;
    }
    
    @Override
    public T findByID(X id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public void save(T object) {
        repository.save(object);
    }
    
    @Override
    @Transactional
    public void deleteByID(X id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}

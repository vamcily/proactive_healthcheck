package com.emc.procheck.storage.service;

public interface TransformService<T, X> {

    /**
     * Query model by id
     * @param id
     * @return
     */
    public T findByID(X id);
    
    public void save(T object);
    
    /**
     * Delete model by id
     * @param id
     * @return
     */
    public void deleteByID(X id);

    public void deleteAll();

}

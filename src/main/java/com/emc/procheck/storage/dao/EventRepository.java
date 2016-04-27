package com.emc.procheck.storage.dao;


import java.util.List;

import com.emc.procheck.storage.model.Event;

/**
 * Repository to access transform models.
 */
public interface EventRepository extends TransformRepository<Event, String>{

    Event findTopBySerialNumberOrderByTimeDesc(String serialNumber);
    
    Event findOneBySystemKey(String systemKey);
    
    List<Event> findBySerialNumber(String serialNumber);
    
    List<Event> findByOeVersionAndOeRevision(String oeVersion, Long oeRevision);
}

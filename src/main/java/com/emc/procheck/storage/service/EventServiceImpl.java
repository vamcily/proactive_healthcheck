package com.emc.procheck.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.EventRepository;
import com.emc.procheck.storage.model.Event;

@Service(value="eventServiceTransform")
public class EventServiceImpl extends TransformServiceImpl<Event, String> implements EventService {

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        super(repository);
    }
    
    @Override
    public List<Event> findAll() {
        return ((EventRepository)repository).findAll();
    }

    @Override
    public Event findLatestBySerialNumber(String serialNumber) {
        return ((EventRepository)repository).findTopBySerialNumberOrderByTimeDesc(serialNumber);
    }
    
    @Override
    public Event findBySystemKey(String systemKey) {
        return ((EventRepository)repository).findOneBySystemKey(systemKey);
    }
    
    @Override
    public List<Event> findBySerialNumber(String serialNumber) {
    	 return ((EventRepository)repository).findBySerialNumber(serialNumber);
    }
    
    @Override
    public List<Event> findBySoftwareVersion(String version) {
        int idx = version.lastIndexOf(".");
        String oeVersion = version.substring(0, idx);
        Long oeRevision =Long.valueOf(version.substring(idx + 1));
        return ((EventRepository)repository).findByOeVersionAndOeRevision(oeVersion, oeRevision);
    }
}

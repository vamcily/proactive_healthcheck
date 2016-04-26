package com.emc.procheck.storage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.Event;
import com.emc.procheck.storage.service.EventService;

/**
 * Rest API used to access Event model
 */
@RestController
public class EventController extends TenantController {

    private final static Logger logger = LoggerFactory.getLogger(EventController.class);
    
    @Autowired
    private EventService eventService;
    
    /**
     * Get data of all events
     * @return List<Event>
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/event/listAll", produces = "application/json")
    public List<Event> listAllEvents() {

        logger.debug("Request model=/event/listAll");
        return eventService.findAll();
    }
    
    
    /**
     * Get data of latest Event by serial number
     * @param serialNumber
     * @return Event
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/event/last", produces = "application/json")
    public Event getLatestEventBySerialNumber(@RequestParam String serialNumber) {

        logger.debug("Request model=/event/last, serialNumber=" + serialNumber);
        return eventService.findLatestBySerialNumber(serialNumber);
    }
    
    /**
     * Get data of Event by systemKey
     * @param systemKey
     * @return Event
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/event/{systemKey}", produces = "application/json")
    public Event getEventBySystemKey(@PathVariable String systemKey) {

        logger.debug("Request model=/event, systemKey=" + systemKey);
        return eventService.findBySystemKey(systemKey);
    }
    
    /**
     * Get data of Event by software version
     * @param softwareVersion
     * @return List of Event
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/event/list", produces = "application/json")
    public List<Event> getEventBySoftwareVersion(@RequestParam String softwareVersion) {

        logger.debug("Request model=/event/list, softwareVersion=" + softwareVersion);
        return eventService.findBySoftwareVersion(softwareVersion);
    }
}

package com.emc.procheck.storage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.UemSystem;
import com.emc.procheck.storage.service.UemSystemService;

/**
 * Rest API used to access Event model
 */
@RestController
public class UemSystemController extends TenantController {

    private final static Logger logger = LoggerFactory.getLogger(UemSystemController.class);
    
    @Autowired
    private UemSystemService uemSysteService;
    
    /**
     * Get data of all systems
     * @return List<Event>
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/system/listAll", produces = "application/json")
    public List<UemSystem> listAllSystems() {

        logger.debug("Request model=/system/listAll");
        return uemSysteService.findAll();
    }
    
    
    /**
     * Get data of systems of specific version
     * @param serialNumber
     * @return Event
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/system/listByVersion", produces = "application/json")
    public List<UemSystem> listSystemsByVersion(@RequestParam String version) {

        logger.debug("Request model=/system/listByVersion, version=" + version);
        return uemSysteService.findByVersion(version);
    }
    
}

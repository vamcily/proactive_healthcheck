package com.emc.procheck.storage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.Software;
import com.emc.procheck.storage.service.SoftwareService;

/**
 * Rest API used to access Software model
 */
@RestController
public class SoftwareController extends TenantController {

    private final static Logger logger = LoggerFactory.getLogger(SoftwareController.class);
    
    @Autowired
    private SoftwareService softwareService;
    
    /**
     * Get data of Software by systemKey
     * @param systemKey
     * @return List of Software
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/software/list", produces = "application/json")
    public List<Software> getSoftware(@RequestParam String systemKey) {

        logger.debug("Request model=/software/list, systemKey=" + systemKey);
        return softwareService.findBySystemKey(systemKey);
    }
}

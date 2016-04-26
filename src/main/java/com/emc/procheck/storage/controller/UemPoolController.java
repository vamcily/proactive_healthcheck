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

import com.emc.procheck.storage.model.UemPool;
import com.emc.procheck.storage.service.UemPoolService;

/**
 * Rest API used to access UemPool model
 */
@RestController
public class UemPoolController extends TenantController {

    private final static Logger logger = LoggerFactory.getLogger(UemPoolController.class);
    
    @Autowired
    UemPoolService uemPoolService;
    
    /**
     * Get data of UemPool by poolKey
     * @param poolKey
     * @return UemPool
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/pool/{poolKey}", produces = "application/json")
    public UemPool getPoolByKey(@PathVariable String poolKey) {

        logger.debug("Request model=/pool, poolKey=" + poolKey);
        return uemPoolService.findByPoolKey(poolKey);
    }
    
    /**
     * Get data of UemPool by systemKey
     * @param systemKey
     * @return List of UemPool
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/pool/list", produces = "application/json")
    public List<UemPool> getPoolBySystemKey(@RequestParam String systemKey) {

        logger.debug("Request model=/pool, systemKey=" + systemKey);
        return uemPoolService.findBySystemKey(systemKey);
    }
}

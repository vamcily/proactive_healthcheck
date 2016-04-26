package com.emc.procheck.storage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.InventoryAttribute;
import com.emc.procheck.storage.service.InventoryAttributeService;

/**
 * Rest API used to access InventoryAttribute model
 */
@RestController
public class InventoryAttributeController extends TenantController {

    private final static Logger logger = LoggerFactory.getLogger(InventoryAttributeController.class);
    
    @Autowired
    private InventoryAttributeService inventoryAttributeService;
    
    /**
     * Get data of InventoryAttribute by inventoryKey
     * @param inventoryKey
     * @return List of InventoryAttribute
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/inventoryAttribute/list", produces = "application/json")
    public List<InventoryAttribute> getInventoryAttribute(@RequestParam String inventoryKey) {

        logger.debug("Request model=/inventoryAttribute/list, inventoryKey=" + inventoryKey);
        return inventoryAttributeService.findByInventoryKey(inventoryKey);
    }
}

package com.emc.procheck.storage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.InventoryFirmware;
import com.emc.procheck.storage.service.InventoryFirmwareService;

/**
 * Rest API used to access InventoryFirmware model
 */
@RestController
public class InventoryFirmwareController extends TenantController {

    private final static Logger logger = LoggerFactory.getLogger(InventoryFirmwareController.class);
    
    @Autowired
    private InventoryFirmwareService inventoryFirmwareService;
    
    /**
     * Get data of InventoryFirmware by inventoryKey
     * @param inventoryKey
     * @return List of InventoryFirmware
     * @throws
     */
    @RequestMapping(method = RequestMethod.GET, value = "/inventoryFirmware/list", produces = "application/json")
    public List<InventoryFirmware> getInventoryFirmware(@RequestParam String inventoryKey) {

        logger.debug("Request model=/inventoryFirmware/list, inventoryKey=" + inventoryKey);
        return inventoryFirmwareService.findByInventoryKey(inventoryKey);
    }
}

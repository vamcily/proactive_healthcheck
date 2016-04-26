
package com.emc.procheck.storage.controller;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.Inventory;
import com.emc.procheck.storage.service.InventoryService;

/**
 * Rest API used to access Inventory model
 */
@RestController
public class InventoryController extends TenantController {

	private final static Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private InventoryService inventoryService;
	

	/**
	 * Get data of Inventory by systemKey and hardwareType @param
	 * systemKey @param hardwareType @return List of Inventory @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/inventory/list", produces = "application/json")
	public ResponseEntity<?> getInventory(@RequestParam(value = "systemKey", required = false) String systemKey,
			@RequestParam(value = "hardwareType", required = false) String hardwareType,
			@RequestParam(value = "raidGroupDisksKey", required = false) String raidGroupDisksKey) {

		logger.debug("Request model=/inventory/list, systemKey=" + systemKey + ", hardwareType="
				+ hardwareType + ", raidGroupDisksKey=" + raidGroupDisksKey);

		if (!StringUtils.isBlank(systemKey) && !StringUtils.isBlank(hardwareType)) {
			logger.debug("Requesting by systemKey and hardwareType");
			List<Inventory> inventories = inventoryService.findAllByHardwareType(systemKey, hardwareType);
			return new ResponseEntity<>(inventories, HttpStatus.OK);
		} else if (!StringUtils.isBlank(raidGroupDisksKey)) {
			logger.debug("Requesting by raidgroupdiskskey");
			List<Inventory> inventories = inventoryService.findDiskByRaidGroupDisksKey(raidGroupDisksKey);
			return new ResponseEntity<>(inventories, HttpStatus.OK);
		}
		logger.error("Unsupported parameters to request inventory.");
		return new ResponseEntity<String>("Invalid parameters", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Get count of Inventory by systemKey and hardwareType
	 * @param systemKey 
	 * @param hardwareType
	 * @param faulted
	 * @return count
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/inventory/countBySystem", produces = "application/json")
	public Long countInventoryBySystemAndHardware(@RequestParam String systemKey, @RequestParam String hardwareType,
			@RequestParam(value = "faulted", required = false) String faulted) {

		logger.debug("Request model=/inventory/count, systemKey=" + systemKey + ", hardwareType="
				+ hardwareType + ", faulted=" + faulted);
		if(!StringUtils.isBlank(faulted)){
			logger.debug("Request to count by systemKey and hardwareType and faulted.");
			Boolean needsReplacement = BooleanUtils.toBoolean(faulted);
			return inventoryService.countBySystemAndHardwareType(systemKey, hardwareType, needsReplacement);
		}else{
			logger.debug("Request to count by systemKey and hardwareType.");
			return inventoryService.countBySystemAndHardwareType(systemKey, hardwareType);
		}
	}

	/**
	 * Get count of Inventory by parentKey and hardwareType 
	 * @param parentKey 
	 * @param hardwareType 
	 * @return count
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/inventory/countByParent", produces = "application/json")
	public Long countInventoryByParentAndHardware(@RequestParam String parentKey, @RequestParam String hardwareType) {

		logger.debug("Request model=/inventory/count, parentKey=" + parentKey + ", hardwareType="
				+ hardwareType);
		return inventoryService.countByParentAndHardwareType(parentKey, hardwareType);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/inventory/{inventoryKey}", produces = "application/json")
	public Inventory getInventoryInstance(@PathVariable String inventoryKey) {

		logger.debug("Request model=/inventory/, inventoryKey=" + inventoryKey);
		return inventoryService.findByID(inventoryKey);
	};

}

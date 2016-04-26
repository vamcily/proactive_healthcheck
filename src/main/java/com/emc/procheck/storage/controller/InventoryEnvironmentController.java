package com.emc.procheck.storage.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.InventoryEnvironment;
import com.emc.procheck.storage.service.InventoryEnvironmentService;

/**
 * Rest API used to access InventoryEnvironment model
 */
@RestController
public class InventoryEnvironmentController extends TenantController {

	private final static Logger logger = LoggerFactory.getLogger(InventoryEnvironmentController.class);

	@Autowired
	private InventoryEnvironmentService inventoryEnvironmentService;
	

	/**
	 * Get data of InventoryEnvironment by inventoryKey @param
	 * inventoryKey @return List of InventoryEnvironment @throws
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/inventoryEnvironment/list", produces = "application/json")
	public ResponseEntity<?> getInventoryEnvironment(
			@RequestParam(value = "inventoryKey", required = false) String inventoryKey,
			@RequestParam(value = "systemKey", required = false) String systemKey,
			@RequestParam(value = "inventoryType", required = false) String inventoryType) {
		logger.debug("Request model=/inventoryEnvironment/list, inventoryKey=" + inventoryKey
				+ ", systemKey=" + systemKey + ", inventoryType=" + inventoryType);

		List<InventoryEnvironment> inventEns = null;
		if (!StringUtils.isBlank(inventoryKey)) {
			logger.debug("Search InventoryEnvironment by inventoryKey: " + inventoryKey);
			inventEns = inventoryEnvironmentService.findByInventoryKey(inventoryKey);
		} else if (!StringUtils.isBlank(systemKey)) {

			if ("temperature".equals(inventoryType)) {
				logger.debug("Search InventoryEnvironment temperature by systemKey: " + systemKey);
				inventEns = inventoryEnvironmentService.findTempBySystemKey(systemKey);
			} else if ("system".equals(inventoryType)) {
			    logger.debug("Search InventoryEnvironment of type " + inventoryType + " for systemKey: " + systemKey);
			    inventEns = inventoryEnvironmentService.findInventoryEnvironment(systemKey, inventoryType);
			}
		}

		if (inventEns != null) {
			return new ResponseEntity<>(inventEns, HttpStatus.OK);
		}
		logger.error("Unsupported parameters: /inventoryEnvironment/list, inventoryKey="
				+ inventoryKey + ", systemKey=" + systemKey + ", inventoryType=" + inventoryType);
		return new ResponseEntity<String>("Invalid parameters", HttpStatus.BAD_REQUEST);
	}

}

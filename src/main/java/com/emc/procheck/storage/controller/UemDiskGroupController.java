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

import com.emc.procheck.storage.model.UemDiskGroup;
import com.emc.procheck.storage.service.UemDiskGroupService;

/**
 * Controller to access DiskGroup transform data.
 */
@RestController
public class UemDiskGroupController extends TenantController {
	private final static Logger logger = LoggerFactory.getLogger(UemDiskGroupController.class);

	@Autowired
	private UemDiskGroupService diskGroupService;

	/**
	 * Get disk group by id.
	 * @param diskGroupKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diskGroup/{diskGroupKey}", produces = "application/json")
	public UemDiskGroup getDiskGroupById(@PathVariable String diskGroupKey) {

		logger.debug("Request model=/diskgroup, diskGroupKey=" + diskGroupKey);
		return diskGroupService.findByID(diskGroupKey);
	}

	/**
	 * Search disk group by systemKey
	 * @param systemKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/diskGroup/list", produces = "application/json")
	public List<UemDiskGroup> searchDiskGroupBySystemKey(@RequestParam String systemKey) {
		logger.debug("Request model=/diskgroup/list, systemKey=" + systemKey);
		return diskGroupService.findBySystemKey(systemKey);
	}

}

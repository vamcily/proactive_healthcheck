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

import com.emc.procheck.storage.model.UemRaidGroup_disks_sub;
import com.emc.procheck.storage.service.UemRaidGroup_disks_subService;

/**
 * Controller to access RaidGroup transform data.
 */
@RestController
public class UemRaidGroup_disks_subController extends TenantController {
	private final static Logger logger = LoggerFactory.getLogger(UemRaidGroup_disks_subController.class);

	@Autowired
	private UemRaidGroup_disks_subService raidGroup_disks_subService;

	/**
	 * Get raid group disks sub by id.
	 * @param diskKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/raidGroup_disks_sub/{diskKey}", produces = "application/json")
	public UemRaidGroup_disks_sub getRaidGroup_disks_subById(@PathVariable String diskKey) {

		logger.debug("Request model=/raidgroup_disks_sub, diskKey=" + diskKey);
		return raidGroup_disks_subService.findByID(diskKey);
	}

	/**
	 * Search raid group disks sub by disksKey
	 * @param disksKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/raidGroup_disks_sub/list", produces = "application/json")
	public List<UemRaidGroup_disks_sub> searchRaidGroup_disks_subByDisksKey(@RequestParam String disksKey) {
		logger.debug("Request model=/raidgroup_disks_sub/list, disksKey=" + disksKey);
		return raidGroup_disks_subService.findByDisksKey(disksKey);
	}

}

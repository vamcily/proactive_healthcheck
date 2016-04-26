package com.emc.procheck.storage.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.storage.model.UemRaidGroup;
import com.emc.procheck.storage.service.UemRaidGroupService;

/**
 * Controller to access RaidGroup transform data.
 */
@RestController
public class UemRaidGroupController extends TenantController {
	private final static Logger logger = LoggerFactory.getLogger(UemRaidGroupController.class);

	@Autowired
	private UemRaidGroupService raidGroupService;

	/**
	 * Get raid group by id.
	 * @param raidGroupKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/raidgroup/{raidGroupKey}", produces = "application/json")
	public UemRaidGroup getRaidGroupById(@PathVariable String raidGroupKey) {

		logger.debug("Request model=/raidgroup, raidGroupKey=" + raidGroupKey);
		return raidGroupService.findByID(raidGroupKey);
	}

	/**
	 * Search raid group by systemKey
	 * @param systemKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/raidgroup/list", produces = "application/json")
	public List<UemRaidGroup> searchRaidGroupBySystemKey(@RequestParam String systemKey,
			@RequestParam(value = "raidType", required = false) String raidType) {
		logger.debug("Request model=/raidgroup/list, systemKey=" + systemKey);
		if(!StringUtils.isBlank(raidType)){
			return raidGroupService.findBySystemKeyAndRaidType(systemKey, raidType);
		}
		return raidGroupService.findBySystemKey(systemKey);
	}
	
}

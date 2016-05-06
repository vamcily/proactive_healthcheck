/**
 * 
 */
package com.emc.procheck.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.rule.model.RuleResult;
import com.emc.procheck.rule.model.StorageComponent;
import com.emc.procheck.rule.service.RuleResultService;

/**
 * @author Eric Wu
 *
 */
@RestController
public class RuleResultController {
	private final static Logger logger = LoggerFactory.getLogger(RuleResultController.class);

	@Autowired
	RuleResultService ruleResultService;

	@RequestMapping(method = RequestMethod.GET, value = "/listRuleResults", produces = "application/json")
	public List<RuleResult> getRuleResults() {
		logger.debug("Request to list rule result ");

		return ruleResultService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listHealthResult/{serialNumber}", produces = "application/json")
	public StorageComponent getHealthResult(@PathVariable String serialNumber) {
		logger.debug("Request to list health check result ");

		return ruleResultService.retrieveHealthResult(serialNumber);
	}
}

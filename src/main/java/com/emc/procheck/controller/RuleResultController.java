/**
 * 
 */
package com.emc.procheck.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/fixComponent", produces = "text/plain; charset=utf-8")
	public ResponseEntity<?> fixComponent(@RequestParam String serialNumber, @RequestParam String component) {
		logger.debug("Request to fix component " + component + " of system " + serialNumber);

		ruleResultService.fixComponent(serialNumber, component);

		String statusStr = "Component fixed";
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.set("X-Service-Status", "OK");

		return new ResponseEntity<>(statusStr, responseHeader, HttpStatus.OK);
	}
}

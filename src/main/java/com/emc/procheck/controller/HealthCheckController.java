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

import com.emc.procheck.rule.model.IRule;
import com.emc.procheck.rule.model.RuleResult;
import com.emc.procheck.rule.service.HealthCheckService;

/**
 * @author Eric Wu
 *
 */
@RestController
public class HealthCheckController {
	private final static Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

	@Autowired
	HealthCheckService healthCheckService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/runRules", produces = "application/json")
	public List<RuleResult> runRules() {
		logger.debug("Request to run rules on all systems");
		
		

		return healthCheckService.runRules();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/healthcheck/{serialNumber}", produces = "application/json")
	public List<RuleResult> runCheck(@PathVariable String serialNumber) {
		logger.debug("Request to run health check on " + serialNumber);

		return healthCheckService.runCheckBySn(serialNumber);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listRules", produces = "application/json")
	public List<IRule> getRules() {
		logger.debug("Request to list health check rules ");

		return healthCheckService.getRules();
	}
}

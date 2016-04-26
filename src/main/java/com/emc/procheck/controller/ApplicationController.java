
package com.emc.procheck.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emc.procheck.config.CommonHealthCheckConfig;


@RestController
public class ApplicationController {
    private final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    
    @Autowired
    CommonHealthCheckConfig commonHealthCheckConfig;
    
    @RequestMapping(value = "/status", produces = "text/plain; charset=utf-8")
    public ResponseEntity<?> appstatus() {
        logger.debug("Request for micro service status");
        
        String statusStr = "Service Name: Health Check Microservice";
        HttpHeaders responseHeader = new HttpHeaders();
        
        statusStr += "\nService Description: This microservice performs health check.";
        statusStr += "\nService Configuration:";
        statusStr += "\n    Health Packet Version: " + commonHealthCheckConfig.getPacketVersion();
        statusStr += "\n    Rule Timeout: " + commonHealthCheckConfig.getRuleTimeout() + " minutes";
        
        responseHeader.set("X-Service-Status", "OK");
        
        return new ResponseEntity<>(statusStr, responseHeader, HttpStatus.OK);
    }

}

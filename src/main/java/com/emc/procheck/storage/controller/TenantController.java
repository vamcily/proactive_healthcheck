package com.emc.procheck.storage.controller;

import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base controller to handle request need tenant.
 */
public class TenantController {

	private final static Logger logger = LoggerFactory.getLogger(TenantController.class);

	/**
	 * handle sql exception caused by missing tenant.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(JpaSystemException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleAppException(JpaSystemException ex) {
		if (ex.getCause() != null && ex.getCause().getCause() != null
				&& ex.getCause().getCause().getClass().equals(SQLGrammarException.class)) {
			logger.error("Tenant is missing in request: " + ex.getMessage());
			return "Please make sure tenant is set in request";
		}
		return ex.getMessage();
	}

}

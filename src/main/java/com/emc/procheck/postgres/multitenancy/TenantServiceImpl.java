package com.emc.procheck.postgres.multitenancy;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {
	private final static Logger logger = LoggerFactory.getLogger(TenantServiceImpl.class);

	// Using ThreadLocal to avoid concurrent conflict. 
	private ThreadLocal<String> currentTenant = new ThreadLocal<String>();

	@PostConstruct
	public void init() {
		logger.info("TenantService.init() - Called");
	}

	public String getCurrentTenant() {
		String tenant = currentTenant.get();
		return tenant != null ? tenant : "NONE";
	}

	public void setCurrentTenant(String tenant) {
		logger.trace("TenantService.setCurrentTenant(tenant=" + tenant + ")");
		currentTenant.set(tenant);
	}
}
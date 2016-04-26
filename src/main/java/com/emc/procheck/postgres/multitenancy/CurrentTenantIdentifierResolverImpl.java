package com.emc.procheck.postgres.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.util.ApplicationContextUtil;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver 
{
	private final static Logger logger = LoggerFactory.getLogger(CurrentTenantIdentifierResolverImpl.class);
    
	public CurrentTenantIdentifierResolverImpl() {
        logger.info("CurrentTenantIdentifierResolverImpl initialized");
	}

	@Override
	public String resolveCurrentTenantIdentifier() {
        logger.trace("resolveCurrentTenantIdentifier()");
        
		TenantService tenantService = (TenantService)ApplicationContextUtil.getBean("tenantServiceImpl");
        if (tenantService == null) {
            return "NONE";
        } 
        
        logger.debug("Current tenant : " + tenantService.getCurrentTenant());
		return tenantService.getCurrentTenant();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}
	
}
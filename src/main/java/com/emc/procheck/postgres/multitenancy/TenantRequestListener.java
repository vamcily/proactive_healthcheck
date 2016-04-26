package com.emc.procheck.postgres.multitenancy;

import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

@Component
public class TenantRequestListener extends RequestContextListener
{
	private final static Logger logger = LoggerFactory.getLogger(TenantRequestListener.class);

	@Autowired
	private TenantService tenantService;
	@Autowired
	private EntityManager entityManager;
    
    private Boolean multiTenancyEnabled = null;
    
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		super.requestInitialized(event);
        
        // if multi tenancy is not enabled then just return.
		if(!isMultiTenancyEnabled())
            return;

		final HttpServletRequest req = (HttpServletRequest) event.getServletRequest();
		String tenantId = getTenantId(req.getParameterMap());
		// TenantService tenantService =
		// (TenantService)ApplicationContextUtil.getBean("tenantServiceImpl");
		tenantService.setCurrentTenant(tenantId);
		
		logger.info("URL: " + req.getRequestURL() + " with tenant as [" + tenantId + "]");
	}

	private String getTenantId(Map<String, String[]> map) {
		Iterator<String> parameters = map.keySet().iterator();
		while (parameters.hasNext()) {
			String parameter = parameters.next();
			if (parameter.equalsIgnoreCase("tid") || 
				parameter.equalsIgnoreCase("tenantid") || 
				parameter.equalsIgnoreCase("tenant")) {
				return map.get(parameter)[0];
			}
		}
		
		logger.trace("tenant id must be specified");
		return "NONE";
	}
    
    /**
     * Check if multi-tenancy is enabled for this Entity manager. 
     * @return true if multitenancy is enabled; false otherwise
     */
	private synchronized boolean isMultiTenancyEnabled() {
        if(multiTenancyEnabled == null) {
        	Map<String,Object> properties = entityManager.getEntityManagerFactory().getProperties();
            if(properties.containsKey(Environment.MULTI_TENANT_CONNECTION_PROVIDER)) {
            	multiTenancyEnabled = true;
            	logger.info("Multi-tenancy is enabled");
            }
            else {
            	multiTenancyEnabled = false;
            	logger.info("Multi-tenancy is disabled");
            }
        }
        return multiTenancyEnabled;
	}
    
}
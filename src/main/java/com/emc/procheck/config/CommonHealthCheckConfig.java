/**
 * 
 */
package com.emc.procheck.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Common health check configuration
 */
@Configuration
@PropertySource(value="classpath:/healthcheck.properties", ignoreResourceNotFound=true)
public class CommonHealthCheckConfig {
    
    public final static String BEANNAME = "commonHealthCheckConfig";
    
    // Unit - minute
    private final static int DEFAULT_RULE_TIMEOUT = 5;
    
    @Autowired
    private Environment env;
    
    public String getPacketVersion() {
        String pv = env.getProperty("healthpacket.version");
        return StringUtils.isEmpty(pv) ? "unknown" : pv;
    }
    
    public int getRuleTimeout() {
        Integer timeout = env.getProperty("rule.timeout", Integer.class);
        return timeout == null ? DEFAULT_RULE_TIMEOUT : timeout;
    }

}

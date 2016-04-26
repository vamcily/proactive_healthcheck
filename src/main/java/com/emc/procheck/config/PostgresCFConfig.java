package com.emc.procheck.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"postgres-cf", "postgres-multitenant-cf"})
public class PostgresCFConfig extends AbstractCloudConfig 
{
	private final static Logger logger=LoggerFactory.getLogger(PostgresCFConfig.class);
	
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() - Initializing CF postgresql datasource");
		
		return connectionFactory().dataSource();
	}
}

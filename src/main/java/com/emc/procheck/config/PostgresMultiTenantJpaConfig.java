package com.emc.procheck.config;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.emc.procheck.postgres.multitenancy.CurrentTenantIdentifierResolverImpl;
import com.emc.procheck.postgres.multitenancy.MultiTenantConnectionProviderImpl;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;

@Configuration
@Profile({"postgres-multitenant-cf" , "postgres-multitenant-local"})
public class PostgresMultiTenantJpaConfig
{
	Logger logger = LoggerFactory.getLogger(PostgresMultiTenantJpaConfig.class);
    
	final static String packages = "com.emc.procheck";
    
	@Autowired
	private org.springframework.core.env.Environment springEnv;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        logger.info("entityManagerFactory initialized");
        
		Map<String, Object> properties = new HashMap<String, Object>();
        
        // ---   Hibernate specific settings
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "false");
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, CurrentTenantIdentifierResolverImpl.class);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, MultiTenantConnectionProviderImpl.class);
        
        String hibernateNamingStrategy = springEnv.getProperty("spring.jpa.hibernate.naming-strategy");
        if(hibernateNamingStrategy != null && hibernateNamingStrategy.length() > 0){
        	properties.put("hibernate.ejb.naming_strategy", hibernateNamingStrategy);
        	logger.debug("Configuring hibernate naming stragegy to be " + hibernateNamingStrategy);
        }
        
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packages);
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        em.setJpaPropertyMap(properties);
        
        return em;
	}
    
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}

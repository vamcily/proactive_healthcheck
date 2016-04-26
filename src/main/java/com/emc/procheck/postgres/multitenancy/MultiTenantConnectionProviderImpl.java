package com.emc.procheck.postgres.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider, ServiceRegistryAwareService
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MultiTenantConnectionProvider.class);
    
	DataSource dataSource;
    
	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		Map<?,?> lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();
        dataSource = (DataSource)lSettings.get("hibernate.connection.datasource");
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public Connection getConnection(String tenant) throws SQLException {
        logger.trace("getConnection for " + tenant);
        final Connection connection = getAnyConnection();
        connection.createStatement().execute("SET SCHEMA '" + tenant + "'");
        return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
	}

	@Override
	public void releaseConnection(String tenant, Connection connection) throws SQLException {
        connection.createStatement().execute("SET SCHEMA 'public'");
        connection.close();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public boolean isUnwrappableAs(Class arg0) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return null;
	}
}
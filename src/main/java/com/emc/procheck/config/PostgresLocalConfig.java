package com.emc.procheck.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;

@Configuration
@Profile({"postgres-local", "postgres-multitenant-local"})
public class PostgresLocalConfig 
{
	private final static Logger logger = LoggerFactory.getLogger(PostgresLocalConfig.class);

    @Value("${database.name}")
    private String dbname;
    @Value("${database.host}")
    private String host;
    @Value("${database.port}")
    private Integer port;
    @Value("${database.user}")
    private String user;
    @Value("${database.password}")
    private String password;
    
	@Bean
	public DataSource dataSource()  throws Exception {
        if(dbname == null || host == null || port == null || user == null)
            throw new IllegalArgumentException("Error: One of the required parameters was missing.  Please specify postgres connection parameters: dbname, host, port, user.");
	    
        logger.info("Initializing local PostgreSQL dataSource: [" + host + "] [" + port + "] [" + user + "]");
        
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(host);
        dataSource.setPortNumber(port);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDatabaseName(dbname);

        return dataSource;
	}
}

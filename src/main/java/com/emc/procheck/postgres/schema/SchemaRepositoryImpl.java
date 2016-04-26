////////////////////////////////////////////////////////////////////////
// Copyright (C) 2015, All Rights Reserved, by
// EMC Corporation, Hopkinton MA.
//
// This software is furnished under a license and may be used and copied
// only  in  accordance  with  the  terms  of such  license and with the
// inclusion of the above copyright notice. This software or  any  other
// copies thereof may not be provided or otherwise made available to any
// other person. No title to and ownership of  the  software  is  hereby
// transferred.
//
// The information in this software is subject to change without  notice
// and  should  not be  construed  as  a commitment by EMC Corporation.
//
// EMC assumes no responsibility for the use or  reliability of its
// software on equipment which is not supplied by EMC.
////////////////////////////////////////////////////////////////////////
package com.emc.procheck.postgres.schema;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emc.procheck.postgres.schema.SchemaRepository;

@Repository
public class SchemaRepositoryImpl implements SchemaRepository 
{    
    private final static Logger logger=LoggerFactory.getLogger(SchemaRepository.class);
    
	@Autowired
	private DataSource dataSource;
    
    /**
     * Checks to see if schema exist
     * Query: select exists (select * from pg_catalog.pg_namespace where nspname = 'ncsu');
     */
    @Override
    public Boolean doesSchemaExist(final String schema) throws Exception {
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        	conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String query = "select exists (select * from pg_catalog.pg_namespace where nspname = '" + schema + "')";
            rs = stmt.executeQuery(query);
            if(rs.next())
                return rs.getBoolean(1);
            return false;
        } 
        catch(Exception e) {
            logger.error("doesSchemaExist() - Failed to check if schema [" + schema + "] exist : " + e.getMessage());
            return false;
        } 
        finally {
            if(rs != null)
                rs.close();
            if(stmt!=null)
                stmt.close();
            if(conn!=null)
                conn.close();
        }
    	
    }

    /**
     * Creates and initializes schema  
     */
    @Override
    public Boolean initializeSchema(final String schema) throws Exception {
    	logger.info("schema [" + schema + "] initialized successfully");
    	return true;
    }

	@Override
	public Boolean dropSchema(String schema) throws Exception {
		logger.info("dropping schema [" + schema + "]");
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
        	conn = dataSource.getConnection();
            stmt = conn.createStatement();
            String query = "DROP SCHEMA '" + schema + "' CASCADE";
            rs = stmt.executeQuery(query);
            logger.error("schema [" + schema + "] dropped successfully");
            return true;
        } 
        catch(Exception e) {
            logger.error("Failed to drop schema [" + schema + "] : " + e.getMessage());
            return false;
        } 
        finally {
            if(rs != null)
                rs.close();
            if(stmt!=null)
                stmt.close();
            if(conn!=null)
                conn.close();
        }
	}
    
}

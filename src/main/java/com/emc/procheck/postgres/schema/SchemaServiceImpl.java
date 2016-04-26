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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.postgres.schema.SchemaRepository;
import com.emc.procheck.postgres.schema.SchemaService;

@Service
public class SchemaServiceImpl implements SchemaService
{
    private final static Logger logger=LoggerFactory.getLogger(SchemaService.class);

    @Autowired
    SchemaRepository repository;

	@Override
	public Boolean doesSchemaExist(String schema) throws Exception {
		return repository.doesSchemaExist(schema);
	}

	@Override
	public Boolean initializeSchema(String schema) throws Exception {
	    logger.trace("initializeSchema() - schema=" + schema);
	    
        if (!doesSchemaExist(schema)) {
            logger.info("schema [ " + schema + " ] does not exist, initializing");
        	return repository.initializeSchema(schema);
        }
        
        return true;
	}

	@Override
	public Boolean dropSchema(String schema) throws Exception {
        if (doesSchemaExist(schema)) {
            logger.info("schema [ " + schema + " ] exist, dropping");
        	return repository.dropSchema(schema);
        }
		return true;
	}
}

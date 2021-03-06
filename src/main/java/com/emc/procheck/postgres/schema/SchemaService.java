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

public interface SchemaService 
{
    Boolean doesSchemaExist(final String schema) throws Exception;
    Boolean initializeSchema(final String schema) throws Exception;
    Boolean dropSchema(final String schema) throws Exception;
}

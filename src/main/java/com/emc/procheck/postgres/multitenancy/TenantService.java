package com.emc.procheck.postgres.multitenancy;

public interface TenantService 
{
    String getCurrentTenant();
    void setCurrentTenant(String tenant);
}
package com.emc.procheck.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.cloud.service.common.MysqlServiceInfo;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationInitializer implements ApplicationContextInitializer<AbstractApplicationContext>
{
    final static Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

    @Override
    public void initialize(AbstractApplicationContext appContext) {
        if (isAnyProfileActive(appContext))
            return;

        logger.info("initializing profiles since no profiles were active");

        Cloud cloud = getCloud();
        List<String> profiles = getCloudProfiles(cloud);
        if (profiles == null) {
            logger.info("Application is not running in the cloud environment. Using local configuration");
            profiles = getLocalProfiles(appContext);
        }

        String[] activeProfiles = appContext.getEnvironment().getActiveProfiles();
        for (String profile: activeProfiles) {
            logger.info("Active Profile: " + profile);
        }        

        for (String profile: profiles) {
            logger.info("Adding profile [" + profile + "] as an active profile");
            appContext.getEnvironment().addActiveProfile(profile);
        }
    }

    private boolean isAnyProfileActive(AbstractApplicationContext appContext) {
        String[] profiles = appContext.getEnvironment().getActiveProfiles();
        for (String profile: profiles) {
            logger.info("profile [" + profile + "] is active");
        }

        return (profiles.length>0);
    }

    private List<String> getCloudProfiles(Cloud cloud) {
        if(cloud == null)
            return null;

        List<String> profiles = new ArrayList<String>();

        List<ServiceInfo> services = cloud.getServiceInfos();
        for(ServiceInfo service: services) {
            if(service instanceof AmqpServiceInfo) {
                profiles.add("amqp");
            }
            if(service instanceof MysqlServiceInfo) {
                profiles.add("mysql");
            }
        }

        profiles.add("cf");

        return profiles;
    }

    private List<String> getLocalProfiles(AbstractApplicationContext appContext) {
        List<String> profiles = new ArrayList<String>();
        profiles.add("local");

        return profiles;
    }

    private Cloud getCloud() {
        try {
            return new CloudFactory().getCloud();
        } catch(CloudException e) {
            return null;
        }
    }

}
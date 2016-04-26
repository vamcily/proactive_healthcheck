/**
 * 
 */
package com.emc.procheck.rule.model.rules;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.model.AbstractRule;
import com.emc.procheck.rule.model.HealthPacket;
import com.emc.procheck.rule.model.RuleResult.RuleStatus;
import com.emc.procheck.storage.model.Event;
import com.emc.procheck.storage.service.EventService;
import com.emc.procheck.util.ApplicationContextUtil;

public class VersionRule extends AbstractRule {

    private String latestVersion;
    private int numOfConfidence; 
    
    private final static Logger logger = LoggerFactory.getLogger(VersionRule.class);
    
    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
    
    public void setNumOfConfidence(int numOfConfidence) {
        this.numOfConfidence = numOfConfidence;
    }

    @Override
    public void execute(String systemKey) {
        if (event.getOeVersion() == null || event.getOeRevision() == null) {
            logger.warn("Version or revision is null for " + systemKey);
            result.setStatus(RuleStatus.NOT_APPLICABLE);
            result.setEndTime(new Date());
            return;
        }
        
        String systemVersion = event.getOeVersion() + "." + event.getOeRevision();
        int cmpResult = 0;
        try {
            cmpResult = compareVersion(latestVersion, systemVersion);
        } catch (NumberFormatException e) {
            // The data sent to server may be corrupted.
            logger.error("Could not parse version field to number.", e);
        }
        
        HealthPacket packet = createHealthPacket();
        packet.setId(UUID.randomUUID().toString());
        packet.setCreated(new Date());
        if (cmpResult < 0) {
            // This should be an internal issue so we still mark pass from user pespective. 
            // It's likely the latest version needs update or the data is from a version which has not been released.
            logger.error("The system version " + systemVersion + " is higher than latest version " + latestVersion);
            packet.setImpact(0);
            result.setStatus(RuleStatus.PASS);
        } 
        else if (cmpResult > 0) {
            packet.setImpact(getImpact());
            int numOfLatest = countSystemWithLatestVersion();
            if (numOfLatest >= numOfConfidence) {
                packet.setDescription("The system is not running the latest software version. " + numOfLatest 
                        + " systems have been running well with version " + latestVersion);
            }
            else {
                packet.setDescription("The system is not running the latest software version.");
            }
            
            List<String> resolution = new ArrayList<String>();
            resolution.add("Upgrade the system to " + latestVersion);
            packet.setRemediation(resolution);
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            packet.setPacketExpiration(cal.getTime());
            
            result.setStatus(RuleStatus.FAIL);
        }
        else {
            packet.setImpact(0);;
            result.setStatus(RuleStatus.PASS);
        }
        
        result.addHealthPacket(event.getSystemKey(), packet);
        result.setEndTime(new Date());
    }
    
    private int compareVersion(String version, String otherVersion) {
        if (version == null && otherVersion == null) {
            return 0;
        }
        if (version != null && otherVersion == null) {
            return 1;
        }
        if (version == null && otherVersion != null) {
            return -1;
        }
        
        String[] vArr = version.split("\\.");
        String[] oArr = otherVersion.split("\\.");
        int num = Math.min(vArr.length, oArr.length);
        for (int i = 0; i < num; i++) {
            int v = Integer.parseInt(vArr[i]);
            int o = Integer.parseInt(oArr[i]);
            if (v > o) {
                return 1;
            } 
            else if (v < o) {
                return -1;
            }
        }
        
        if (vArr.length > oArr.length) {
            return 1;
        }
        else if (vArr.length > oArr.length) {
            return -1;
        }
        return 0;
    }

    private int countSystemWithLatestVersion() {
        EventService service = (EventService) ApplicationContextUtil.getBean(EventService.BEANNAME);
        List<Event> events = service.findBySoftwareVersion(latestVersion);
        if (events == null || events.isEmpty()) {
            logger.info("No event record with version " + latestVersion);
            return 0;
        }
        
        Set<String> systems = new HashSet<String>();
        for (Event event : events) {
            systems.add(event.getSerialNumber());
        }
        
        return systems.size();
    }

}

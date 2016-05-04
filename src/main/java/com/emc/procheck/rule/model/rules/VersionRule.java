/**
 * 
 */
package com.emc.procheck.rule.model.rules;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.model.AbstractRule;
import com.emc.procheck.rule.model.RuleResult.RuleStatus;
import com.emc.procheck.storage.model.UemSystem;
import com.emc.procheck.storage.service.UemSystemService;
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
        if (system.getVersion() == null) {
            logger.warn("Version is null for " + systemKey);
            result.setStatus(RuleStatus.NOT_APPLICABLE);
            result.setEndTime(new Date());
            return;
        }
        
        String systemVersion = system.getVersion();
        int cmpResult = 0;
        try {
            cmpResult = compareVersion(latestVersion, systemVersion);
        } catch (NumberFormatException e) {
            // The data sent to server may be corrupted.
            logger.error("Could not parse version field to number.", e);
        }
        
        if (cmpResult < 0) {
            // This should be an internal issue so we still mark pass from user pespective. 
            // It's likely the latest version needs update or the data is from a version which has not been released.
            logger.error("The system version " + systemVersion + " is higher than latest version " + latestVersion);
            result.setScore(100);
            result.setStatus(RuleStatus.PASS);
        } 
        else if (cmpResult > 0) {
        	result.setScore(0);
            int numOfLatest = countSystemWithLatestVersion();
            if (numOfLatest >= numOfConfidence) {
                result.addAction("The system is not running the latest software version. " + numOfLatest 
                        + " systems have been running well with version " + latestVersion);
            }
            else {
            	result.addAction("The system is not running the latest software version.");
            }
            
            result.setStatus(RuleStatus.FAIL);
        }
        else {
        	result.setScore(100);
            result.setStatus(RuleStatus.PASS);
        }
        
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
        UemSystemService service = (UemSystemService) ApplicationContextUtil.getBean(UemSystemService.BEANNAME);
        List<UemSystem> systems = service.findByVersion(latestVersion);
        if (systems == null || systems.isEmpty()) {
            logger.info("No system record with version " + latestVersion);
            return 0;
        }
        
        return systems.size();
    }

}

/**
 * 
 */
package com.emc.procheck.rule.model;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author Eric Wu
 *
 */

public class RuleResult {
    
    public static enum RuleStatus { NOT_RUN, RUNNING, NOT_APPLICABLE, PASS, FAIL };
    
    private RuleStatus status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private HashMap<String, HealthPacket> healthPacketMap = new HashMap<String, HealthPacket>();
    
    public RuleStatus getStatus() {
        return status;
    }
    
    public void setStatus(RuleStatus status) {
        this.status = status;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void addHealthPacket(String key, HealthPacket packet) {
        healthPacketMap.put(key, packet);
    }
    
    public HashMap<String, HealthPacket> getHealthPacketMap() {
        return healthPacketMap;
    }
    
    public HealthPacket getHealthPacket(String key) {
        return healthPacketMap.get(key);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

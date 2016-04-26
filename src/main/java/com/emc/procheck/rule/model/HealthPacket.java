/**
 * 
 */
package com.emc.procheck.rule.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Eric Wu
 *
 */
public class HealthPacket {
    
    // unique id for this packet
    private String id;
    // the version of health package (structure)
    private String packetVer;
    // date/time of packet creation
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;
    // serial no of product affected
    private String serialNo; 
    // unique name for uService that created packet
    private String uService;
    // sub packet level, can be Empty. Allows uService to publish sub-level hierarchy
    private String packetSubLevel;
    // absolute impact of this item on systems health
    private int impact;
    // version of EMC standard rule set
    private String stdVer;
    // temporal aspects (how long packet info is viable for aggregation score)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date packetExpiration;
    // short description of health awareness situation/problem
    private String description;
    // actions to take to correct situation OR link to complex remediation steps
    private List<String> remediation;
    // date/time of data set creation
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sourceDataCreation;
    // provide ability for raw data pass through to higher level access
    private Map<String, String> rawData;
    // placeholder for future baggage
    private Map<String, String> supplemental;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPacketVer() {
        return packetVer;
    }
    public void setPacketVer(String packetVer) {
        this.packetVer = packetVer;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getSerialNo() {
        return serialNo;
    }
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    public String getuService() {
        return uService;
    }
    public void setuService(String uService) {
        this.uService = uService;
    }
    public String getPacketSubLevel() {
        return packetSubLevel;
    }
    public void setPacketSubLevel(String packetSubLevel) {
        this.packetSubLevel = packetSubLevel;
    }
    public int getImpact() {
        return impact;
    }
    public void setImpact(int impact) {
        this.impact = impact;
    }
    public String getStdVer() {
        return stdVer;
    }
    public void setStdVer(String stdVer) {
        this.stdVer = stdVer;
    }
    public Date getPacketExpiration() {
        return packetExpiration;
    }
    public void setPacketExpiration(Date packetExpiration) {
        this.packetExpiration = packetExpiration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getRemediation() {
        return remediation;
    }
    public void setRemediation(List<String> remediation) {
        this.remediation = remediation;
    }
    public Date getSourceDataCreation() {
        return sourceDataCreation;
    }
    public void setSourceDataCreation(Date sourceDataCreation) {
        this.sourceDataCreation = sourceDataCreation;
    }
    public Map<String, String> getRawData() {
        return rawData;
    }
    public void setRawData(Map<String, String> rawData) {
        this.rawData = rawData;
    }
    public Map<String, String> getSupplemental() {
        return supplemental;
    }
    public void setSupplemental(Map<String, String> supplemental) {
        this.supplemental = supplemental;
    }
}

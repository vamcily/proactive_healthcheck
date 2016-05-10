package com.emc.procheck.storage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_event_ent")
@Table(name = "uem_event")
public class Event {

    @Id
    @GeneratedValue(generator = "systemKeyGenerator")
    @GenericGenerator(name = "systemKeyGenerator", strategy = "assigned")
	private String systemKey;
	private String model;
	private String internalModel;
	private String serialNumber;
	private String platform;
	private String name;
	private Boolean isUpgradeComplete;
	private Boolean isAutoFailbackEnabled;
	private Boolean isEULAAccepted;
	private Boolean isFipsEnabled;
	private Boolean isSsoEnabled;
	private Boolean isTLS1Enabled;
	private Date time;
	private String rebootPrivilege;
	private String systemUUID;
	private String licenseActivationKey;
	private String scriptVersion;
	private Boolean showPrivateData;
	private String groups;
	private Double duration;
	private String capturestatus;
	private String oeVersion;
	private Long oeRevision;
	private Integer score;

	public Event() {
	}

	public Event(String systemKey) {
		setSystemKey(systemKey);
	}

	@Column(insertable = false, updatable = false)
	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getInternalModel() {
		return internalModel;
	}

	public void setInternalModel(String internalModel) {
		this.internalModel = internalModel;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsUpgradeComplete() {
		return isUpgradeComplete;
	}

	public void setIsUpgradeComplete(Boolean isUpgradeComplete) {
		this.isUpgradeComplete = isUpgradeComplete;
	}

	public Boolean getIsAutoFailbackEnabled() {
		return isAutoFailbackEnabled;
	}

	public void setIsAutoFailbackEnabled(Boolean isAutoFailbackEnabled) {
		this.isAutoFailbackEnabled = isAutoFailbackEnabled;
	}

	public Boolean getIsEULAAccepted() {
		return isEULAAccepted;
	}

	public void setIsEULAAccepted(Boolean isEULAAccepted) {
		this.isEULAAccepted = isEULAAccepted;
	}

	public Boolean getIsFipsEnabled() {
		return isFipsEnabled;
	}

	public void setIsFipsEnabled(Boolean isFipsEnabled) {
		this.isFipsEnabled = isFipsEnabled;
	}

	public Boolean getIsSsoEnabled() {
		return isSsoEnabled;
	}

	public void setIsSsoEnabled(Boolean isSsoEnabled) {
		this.isSsoEnabled = isSsoEnabled;
	}

	public Boolean getIsTLS1Enabled() {
		return isTLS1Enabled;
	}

	public void setIsTLS1Enabled(Boolean isTLS1Enabled) {
		this.isTLS1Enabled = isTLS1Enabled;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRebootPrivilege() {
		return rebootPrivilege;
	}

	public void setRebootPrivilege(String rebootPrivilege) {
		this.rebootPrivilege = rebootPrivilege;
	}

	public String getSystemUUID() {
		return systemUUID;
	}

	public void setSystemUUID(String systemUUID) {
		this.systemUUID = systemUUID;
	}

	public String getLicenseActivationKey() {
		return licenseActivationKey;
	}

	public void setLicenseActivationKey(String licenseActivationKey) {
		this.licenseActivationKey = licenseActivationKey;
	}

	public String getScriptVersion() {
		return scriptVersion;
	}

	public void setScriptVersion(String scriptVersion) {
		this.scriptVersion = scriptVersion;
	}

	public Boolean getShowPrivateData() {
		return showPrivateData;
	}

	public void setShowPrivateData(Boolean showPrivateData) {
		this.showPrivateData = showPrivateData;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public String getCapturestatus() {
		return capturestatus;
	}

	public void setCapturestatus(String capturestatus) {
		this.capturestatus = capturestatus;
	}

	public String getOeVersion() {
		return oeVersion;
	}

	public void setOeVersion(String oeVersion) {
		this.oeVersion = oeVersion;
	}

	public Long getOeRevision() {
		return oeRevision;
	}

	public void setOeRevision(Long oeRevision) {
		this.oeRevision = oeRevision;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

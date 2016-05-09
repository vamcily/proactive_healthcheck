package com.emc.procheck.storage.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity(name = "uem_lun_ent")
@Table(name = "uem_lun")

public class UemLun {
	
	@Id
	@GeneratedValue(generator = "systemKeyGenerator")
	@GenericGenerator(name = "systemKeyGenerator", strategy = "assigned")
	
	private Integer lunId;
	private String  lunKey;
	private String  systemKey;
	private String  serialNumber;
	private String  isReplicationDestination;
	private String  isSnapSchedulePaused;
	private String  isThinEnabled;
	private Long    metadataSize;
	private Long    metadataSizeAllocated;
	private String  name;
	private Long    sizeAllocated;
	private Long    sizeTotal;
	private Long    sizeUsed;
	private Long    snapCount;
	private String  snapsShedulekey;
	private Long    snapsSize;
	private Long    snapsSizeAllocated;
	private String  storageResourceKey;
	private String  tieringPolicy;
	private String  type;
	
	
	public UemLun() {
	}
	
	public UemLun(String systemKey) {
		setSystemKey(systemKey);
	}
	
	public Integer getLunId() {
		return this.lunId;
	}
	
	public void setLunId(Integer lunId) {
		this.lunId = lunId;
	}
	
	public String getLunKey() {
		return this.lunKey;
	}
	
	public void setLunKey(String lunKey) {
		this.lunKey = lunKey;
	}
	
	public String getSystemKey() {
		return this.systemKey;
	}
	
	public void setSystemKey(String systemKey) {
		this.systemKey =systemKey;
	}
	
	public String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setSerialnumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getIsReplicationDestination() {
		return this.isReplicationDestination;
	}
	
	public void setIsReplicationDestination(String isReplicationDestination) {
		this.isReplicationDestination = isReplicationDestination;
	}
	
	public String getIsSnapSchedulePaused() {
		return this.isSnapSchedulePaused;
	}
	public void setIsSnapSchedulePaused(String isSnapSchedulePaused) {
		this.isSnapSchedulePaused = isSnapSchedulePaused;
	}
	
	public String getIsThinEnabled() {
		return this.isThinEnabled;
	}
	public void setIsThinEnabled(String isThinEnabled) {
		this.isThinEnabled = isThinEnabled;
	}
	
	public Long getMetadataSize() {
		return this.metadataSize;
	}
	public void setMetadataSize(Long metadataSize) {
		this.metadataSize = metadataSize;
	}
	
	public Long getMetadataSizeAllocated() {
		return this.metadataSizeAllocated;
	}
	public void setMetadataSizeAllocated(Long metadataSizeAllocated) {
		this.metadataSizeAllocated = metadataSizeAllocated;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getSizeAllocated() {
		return this.sizeAllocated;
	}
	public void setSizeAllocated(Long sizeAllocated) {
		this.sizeAllocated = sizeAllocated;
	}
	
	public Long getSizeTotal() {
		return this.sizeTotal;
	}
	public void setSizeTotal(Long sizeTotal) {
		this.sizeTotal = sizeTotal;
	}
	
	public Long getSizeUsed() {
		return this.sizeUsed;
	}
	public void setSizeUsed(Long sizeUsed) {
		this.sizeUsed = sizeUsed;
	}
	
	public Long getSnapCount() {
		return this.snapCount;
	}
	public void setSnapCount(Long snapCount) {
		this.snapCount = snapCount;
	}
	
	public String getSnapsShedulekey() {
		return this.snapsShedulekey;
	}
	public void setSnapsShedulekey(String snapsShedulekey) {
		this.snapsShedulekey = snapsShedulekey;
	}
	
	public Long getSnapsSize() {
		return this.snapsSize;
	}
	public void setSnapsSize(Long snapsSize) {
		this.snapsSize = snapsSize;
	}
	
	public Long getSnapsSizeAllocated() {
		return this.snapsSizeAllocated;
	}
	public void setSnapsSizeAllocated(Long snapsSizeAllocated) {
		this.snapsSizeAllocated = snapsSizeAllocated;
	}
	
	public String getStorageResourceKey() {
		return this.storageResourceKey;
	}
	public void setStorageResourceKey(String storageResourceKey) {
		this.storageResourceKey = storageResourceKey;
	}
	
	public String getTieringPolicy() {
		return this.tieringPolicy;
	}
	public void setTieringPolicy(String tieringPolicy) {
		this.tieringPolicy = tieringPolicy;
	}
	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

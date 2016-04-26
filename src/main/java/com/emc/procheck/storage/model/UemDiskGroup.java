package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_diskgroup_ent")
@Table(name = "uem_diskgroup")
public class UemDiskGroup {

    @Id
    @GeneratedValue(generator = "diskGroupKeyGenerator")
    @GenericGenerator(name = "diskGroupKeyGenerator", strategy = "assigned")
	private String diskGroupKey;
	private String hotSparePolicyStatus;
	private Boolean isFASTCacheAllowable;
	private Integer minHotSpareCandidates;
	private String systemKey;
	private String tierType;
	private Integer totalDisks;
	private Integer unconfiguredDisks;

	public UemDiskGroup() {
	}

	public UemDiskGroup(String diskGroupKey) {
		setDiskGroupKey(diskGroupKey);
	}

	@Column(insertable = false, updatable = false)
	public String getDiskGroupKey() {
		return diskGroupKey;
	}

	public void setDiskGroupKey(String diskGroupKey) {
		this.diskGroupKey = diskGroupKey;
	}

	public String getHotSparePolicyStatus() {
		return hotSparePolicyStatus;
	}

	public void setHotSparePolicyStatus(String hotSparePolicyStatus) {
		this.hotSparePolicyStatus = hotSparePolicyStatus;
	}

	public Boolean getIsFASTCacheAllowable() {
		return isFASTCacheAllowable;
	}

	public void setIsFASTCacheAllowable(Boolean isFASTCacheAllowable) {
		this.isFASTCacheAllowable = isFASTCacheAllowable;
	}

	public Integer getMinHotSpareCandidates() {
		return minHotSpareCandidates;
	}

	public void setMinHotSpareCandidates(Integer minHotSpareCandidates) {
		this.minHotSpareCandidates = minHotSpareCandidates;
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public String getTierType() {
		return tierType;
	}

	public void setTierType(String tierType) {
		this.tierType = tierType;
	}

	public Integer getTotalDisks() {
		return totalDisks;
	}

	public void setTotalDisks(Integer totalDisks) {
		this.totalDisks = totalDisks;
	}

	public Integer getUnconfiguredDisks() {
		return unconfiguredDisks;
	}

	public void setUnconfiguredDisks(Integer unconfiguredDisks) {
		this.unconfiguredDisks = unconfiguredDisks;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_raidgroup_ent")
@Table(name = "uem_raidgroup")
public class UemRaidGroup {

	private String diskGroupKey;
	private String disksKey;
	private String healthValue;
	private Integer parityDisks;
	private String poolKey;
    @Id
    @GeneratedValue(generator = "raidGroupKeyGenerator")
    @GenericGenerator(name = "raidGroupKeyGenerator", strategy = "assigned")
    private String raidGroupKey;
	private String raidType;
	private Long sizeTotal;
	private Integer stripeWidth;
	private String systemKey;
	private String tierType;
	private String type;

	public UemRaidGroup() {
	}
	
	public UemRaidGroup(String raidGroupKey) {
		setRaidGroupKey(raidGroupKey);
	}

	public String getDiskGroupKey() {
		return diskGroupKey;
	}

	public void setDiskGroupKey(String diskGroupKey) {
		this.diskGroupKey = diskGroupKey;
	}

	public String getDisksKey() {
		return disksKey;
	}

	public void setDisksKey(String disksKey) {
		this.disksKey = disksKey;
	}

	public Integer getParityDisks() {
		return parityDisks;
	}

	public void setParityDisks(Integer parityDisks) {
		this.parityDisks = parityDisks;
	}

	public String getPoolKey() {
		return poolKey;
	}

	public void setPoolKey(String poolKey) {
		this.poolKey = poolKey;
	}

	@Column(insertable = false, updatable = false)
	public String getRaidGroupKey() {
		return raidGroupKey;
	}

	public void setRaidGroupKey(String raidGroupKey) {
		this.raidGroupKey = raidGroupKey;
	}

	public String getRaidType() {
		return raidType;
	}

	public void setRaidType(String raidType) {
		this.raidType = raidType;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHealthValue() {
		return healthValue;
	}

	public void setHealthValue(String healthValue) {
		this.healthValue = healthValue;
	}

	public Long getSizeTotal() {
		return sizeTotal;
	}

	public void setSizeTotal(Long sizeTotal) {
		this.sizeTotal = sizeTotal;
	}

	public Integer getStripeWidth() {
		return stripeWidth;
	}

	public void setStripeWidth(Integer stripeWidth) {
		this.stripeWidth = stripeWidth;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

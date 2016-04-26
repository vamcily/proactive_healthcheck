package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_environment_ent")
@Table(name = "uem_environment")
public class InventoryEnvironment {

    @Id
    @GeneratedValue(generator = "inventoryKeyGenerator")
    @GenericGenerator(name = "inventoryKeyGenerator", strategy = "assigned")
	private String inventoryKey;
	private Integer averagePowerW;
	private Integer averageTempC;
	private Integer currentPowerW;
	private Integer currentTempC;
	private Integer maxPowerW;
	private Integer maxTempC;

	public InventoryEnvironment() {

	}

	public InventoryEnvironment(String inventoryKey) {
		setInventoryKey(inventoryKey);
	}

	@Column(insertable = false, updatable = false)
	public String getInventoryKey() {
		return inventoryKey;
	}

	public void setInventoryKey(String inventoryKey) {
		this.inventoryKey = inventoryKey;
	}

	public Integer getAveragePowerW() {
		return averagePowerW;
	}

	public void setAveragePowerW(Integer averagePowerW) {
		this.averagePowerW = averagePowerW;
	}

	public Integer getAverageTempC() {
		return averageTempC;
	}

	public void setAverageTempC(Integer averageTempC) {
		this.averageTempC = averageTempC;
	}

	public Integer getCurrentPowerW() {
		return currentPowerW;
	}

	public void setCurrentPowerW(Integer currentPowerW) {
		this.currentPowerW = currentPowerW;
	}

	public Integer getCurrentTempC() {
		return currentTempC;
	}

	public void setCurrentTempC(Integer currentTempC) {
		this.currentTempC = currentTempC;
	}

	public Integer getMaxPowerW() {
		return maxPowerW;
	}

	public void setMaxPowerW(Integer maxPowerW) {
		this.maxPowerW = maxPowerW;
	}

	public Integer getMaxTempC() {
		return maxTempC;
	}

	public void setMaxTempC(Integer maxTempC) {
		this.maxTempC = maxTempC;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

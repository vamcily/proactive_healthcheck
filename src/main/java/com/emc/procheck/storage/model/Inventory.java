package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_inventory_ent")
@Table(name = "uem_inventory")
public class Inventory {

    @Id
    @GeneratedValue(generator = "inventoryKeyGenerator")
    @GenericGenerator(name = "inventoryKeyGenerator", strategy = "assigned")
	private String inventoryKey;
	private String systemKey;
	private String parentKey;
	private String nickname;
	private String emcPartNumber;
	private String emcSerialNumber;
	private String vendorPartNumber;
	private String vendorSerialNumber;
	private String model;
	private String name;
	private String manufacturer;
	private Boolean needsReplacement;
	private String healthValue;
	private Integer slotNumber;

	public Inventory() {
	}

	public Integer getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getParentKey() {
		return this.parentKey;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmcPartNumber() {
		return emcPartNumber;
	}

	public void setEmcPartNumber(String emcPartNumber) {
		this.emcPartNumber = emcPartNumber;
	}

	public String getEmcSerialNumber() {
		return emcSerialNumber;
	}

	public void setEmcSerialNumber(String emcSerialNumber) {
		this.emcSerialNumber = emcSerialNumber;
	}

	public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Boolean getNeedsReplacement() {
		return needsReplacement;
	}

	public void setNeedsReplacement(Boolean needsReplacement) {
		this.needsReplacement = needsReplacement;
	}

	@Column(insertable = false, updatable = false)
	public String getInventoryKey() {
		return inventoryKey;
	}

	public void setInventoryKey(String inventoryKey) {
		this.inventoryKey = inventoryKey;
	}

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

	public String getVendorSerialNumber() {
		return vendorSerialNumber;
	}

	public void setVendorSerialNumber(String vendorSerialNumber) {
		this.vendorSerialNumber = vendorSerialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHealthValue() {
		return healthValue;
	}

	public void setHealthValue(String healthValue) {
		this.healthValue = healthValue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

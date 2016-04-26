package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "uem_inventory_firmware_ent")
@Table(name = "uem_inventory_firmware")
public class InventoryFirmware {

    @Id
    @SequenceGenerator(name = "uem_inventory_firmware_firmwarekey_seq", sequenceName = "uem_inventory_firmware_firmwarekey_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uem_inventory_firmware_firmwarekey_seq")
	private Long firmwareKey;
	private String inventoryKey;
	private String firmwareType;
	private String firmwareValue;

	@Column(insertable=false, updatable=false)
	public Long getFirmwareKey() {
		return firmwareKey;
	}

	public void setFirmwareKey(Long firmwareKey) {
		this.firmwareKey = firmwareKey;
	}

	public String getInventoryKey() {
		return inventoryKey;
	}

	public void setInventoryKey(String inventoryKey) {
		this.inventoryKey = inventoryKey;
	}

	public String getFirmwareType() {
		return firmwareType;
	}

	public void setFirmwareType(String firmwareType) {
		this.firmwareType = firmwareType;
	}

	public String getFirmwareValue() {
		return firmwareValue;
	}

	public void setFirmwareValue(String firmwareValue) {
		this.firmwareValue = firmwareValue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

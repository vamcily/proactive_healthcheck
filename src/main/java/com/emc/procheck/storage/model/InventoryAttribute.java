package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "uem_inventory_attributes_ent")
@Table(name = "uem_inventory_attributes")
public class InventoryAttribute {

    @Id
    @SequenceGenerator(name = "uem_inventory_attributes_attributekey_seq", sequenceName = "uem_inventory_attributes_attributekey_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uem_inventory_attributes_attributekey_seq")
	private Long attributeKey;

	private String inventoryKey;
	private String attributeName;
	private String attributeValue;

	@Column(insertable = false, updatable = false)
	public Long getAttributeKey() {
		return attributeKey;
	}

	public void setAttributeKey(Long attributeKey) {
		this.attributeKey = attributeKey;
	}

	public String getInventoryKey() {
		return inventoryKey;
	}

	public void setInventoryKey(String inventoryKey) {
		this.inventoryKey = inventoryKey;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

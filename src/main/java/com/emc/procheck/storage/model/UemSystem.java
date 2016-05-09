package com.emc.procheck.storage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_system_view_ent")
@Table(name = "uem_system_view")
public class UemSystem {

	private String customerId; 
	private String customerName;
	private String siteId;
	private String siteName;
	private String systemKey;
	
	@Id
    @GeneratedValue(generator = "snGenerator")
    @GenericGenerator(name = "snGenerator", strategy = "assigned")
    private String serialNumber;
	
	private String model;
	private String version;
	
	public UemSystem() {
		
	}
	
	public UemSystem(String serialNumber) {
		setSerialNumber(serialNumber);
	}
	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

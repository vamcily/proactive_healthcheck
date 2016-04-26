package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "uem_software_ent")
@Table(name = "uem_software")
public class Software {
    @Id
    @SequenceGenerator(name = "uem_software_softwarekey_seq", sequenceName = "uem_software_softwarekey_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uem_software_softwarekey_seq")
	private Long softwareKey;
	private String systemKey;
	private String softwareType;
	private String name;
	private String version;

	@Column(insertable = false, updatable = false)
	public Long getSoftwareKey() {
		return softwareKey;
	}

	public void setSoftwareKey(Long softwareKey) {
		this.softwareKey = softwareKey;
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSoftwareType() {
		return softwareType;
	}

	public void setSoftwareType(String softwareType) {
		this.softwareType = softwareType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

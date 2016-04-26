package com.emc.procheck.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_raidgroup_disks_sub_ent")
@Table(name = "uem_raidgroup_disks_sub")
public class UemRaidGroup_disks_sub {

	private String diskKey;
	
    @Id
    @GeneratedValue(generator = "disksKeyGenerator")
    @GenericGenerator(name = "disksKeyGenerator", strategy = "assigned")
    private String disksKey;

	public UemRaidGroup_disks_sub() {
	}

	public UemRaidGroup_disks_sub(String disksKey) {
		setDisksKey(disksKey);
	}

	@Column(insertable = false, updatable = false)
	public String getDiskKey() {
		return diskKey;
	}

	public void setDiskKey(String diskKey) {
		this.diskKey = diskKey;
	}

	public String getDisksKey() {
		return disksKey;
	}

	public void setDisksKey(String disksKey) {
		this.disksKey = disksKey;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

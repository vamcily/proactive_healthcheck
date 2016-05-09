package com.emc.procheck.storage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "uem_rule_ent")
@Table(name = "uem_rule")
public class RuleInfo {

    @Id
    @GeneratedValue(generator = "systemKeyGenerator")
    @GenericGenerator(name = "systemKeyGenerator", strategy = "assigned")
	private String id;	
	private boolean isSelected;

	public RuleInfo() {
	}

	public RuleInfo(String id) {
		setId(id);
	}

	@Column(insertable = true, updatable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

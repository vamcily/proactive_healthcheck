/**
 * 
 */
package com.emc.procheck.rule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;



/**
 * @author Eric Wu
 *
 */
@Entity(name = "rule_result_ent")
@Table(name = "rule_result")
public class RuleResult {
    
    public static enum RuleStatus { NOT_RUN, RUNNING, NOT_APPLICABLE, PASS, FAIL };
    
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    private String id;
    
    @Enumerated
    private RuleStatus status;
    private Date startTime;
    private Date endTime;
    
    private int score; 
    private double weight; 
    private String component;
    private String ruleId;
    private String ruleName;
    private String systemKey;
    private String serialNumber;
    
    @ElementCollection
    @CollectionTable(
        name = "rule_action",
        joinColumns=@JoinColumn(name = "id", referencedColumnName = "id")
    )
    @Column(name="action")
    private List<String> actions;
    
    public RuleResult() {
    	actions = new ArrayList<String>();
    }
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RuleStatus getStatus() {
        return status;
    }
    
    public void setStatus(RuleStatus status) {
        this.status = status;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}
	
	public List<String> getActions() {
		return actions;
	}
	
	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public void addAction(String action) {
		actions.add(action);
	}
	
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
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

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

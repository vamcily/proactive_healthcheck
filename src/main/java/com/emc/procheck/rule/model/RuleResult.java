/**
 * 
 */
package com.emc.procheck.rule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author Eric Wu
 *
 */

public class RuleResult {
    
    public static enum RuleStatus { NOT_RUN, RUNNING, NOT_APPLICABLE, PASS, FAIL };
    
    private RuleStatus status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    
    private int score; 
    private double weight; 
    private String component;
    private List<String> actions;
    
    public RuleResult() {
    	actions = new ArrayList<String>();
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

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

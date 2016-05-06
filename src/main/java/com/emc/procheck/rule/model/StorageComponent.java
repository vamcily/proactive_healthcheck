package com.emc.procheck.rule.model;

import java.util.ArrayList;
import java.util.List;

public class StorageComponent {
	
	private String name;
	private int score;
	private double weight; 
	private List<String> actions;
	private List<StorageComponent> children;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public List<String> getActions() {
		return actions;
	}
	
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	
	synchronized public void addActions(List<String> actions) {
		if (this.actions == null) {
			this.actions = new ArrayList<String>();
		}
		this.actions.addAll(actions);
	}
	
	public List<StorageComponent> getChildren() {
		return children;
	}
	
	public void setChildren(List<StorageComponent> childern) {
		this.children = childern;
	}
	
	synchronized public void addChild(StorageComponent child) {
		if (this.children == null) {
			this.children = new ArrayList<StorageComponent>();
		}
		this.children.add(child);
	}
}

/**
 * 
 */
package com.emc.procheck.rule.model;


/**
 * @author Eric Wu
 *
 */
public interface IRule {
    
    public String getId();
    public String getName();
    public String getDescription();
    public double getWeight();
    public RuleResult getResult();
    
    public void check(String systemKey);
}

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
    public int getImpact();
    public RuleResult getResult();
    
    public void check(String systemKey);
    public void checkWithoutTenant(String sn, String systemKey);
}

/**
 * 
 */
package com.emc.procheck.rule.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.model.RuleResult.RuleStatus;
import com.emc.procheck.storage.model.UemSystem;
import com.emc.procheck.storage.service.UemSystemService;
import com.emc.procheck.util.ApplicationContextUtil;

/**
 * @author Eric Wu
 *
 */
public abstract class AbstractRule implements IRule, Cloneable {

	private String id;
	private String name;
	private String description;
	private String category;
	private double weight;
	protected String applicableVersion;
	protected String applicableModel;
	protected RuleResult result;
	
	protected UemSystem system;
	
	protected static final int MAX_SCORE = 100;

	private final static Logger logger = LoggerFactory.getLogger(AbstractRule.class);

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setApplicableVersion(String applicableVersion) {
		this.applicableVersion = applicableVersion;
	}

	public void setApplicableModel(String applicableModel) {
		this.applicableModel = applicableModel;
	}

	@Override
	public RuleResult getResult() {
		return result;
	}

	@Override
	public void check(String systemKey) {

		result = new RuleResult();

		UemSystemService service = (UemSystemService) ApplicationContextUtil.getBean(UemSystemService.BEANNAME);
		system = service.findBySystemKey(systemKey);
		if (system == null) {
			logger.error("Could not find the system object with systemKey " + systemKey);
			result.setStatus(RuleStatus.NOT_APPLICABLE);
			return;
		}

		if (!checkIfApplicable(systemKey)) {
			result.setStatus(RuleStatus.NOT_APPLICABLE);
			return;
		}

		result.setStartTime(new Date());
		result.setStatus(RuleStatus.RUNNING);
		result.setWeight(weight);
		
		execute(systemKey);

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private boolean checkIfApplicable(String systemKey) {
		if (system.getVersion() == null) {
			logger.warn("Version is null for " + systemKey);
			return false;
		}

		if (system.getModel() == null) {
			logger.warn("Model is null for " + systemKey);
			return false;
		}

		String systemVersion = system.getVersion();

		Pattern pattern = Pattern.compile(applicableVersion);
		Matcher matcher = pattern.matcher(systemVersion);

		if (!matcher.matches()) {
			logger.info("The rule " + name + " could not be applied to " + systemKey + ". System version "
					+ systemVersion + " doesn't match rule version " + applicableVersion + ".");
			return false;
		}

		pattern = Pattern.compile(applicableModel);
		String systemModel = system.getModel();
		matcher = pattern.matcher(systemModel);
		if (!matcher.matches()) {
			logger.info("The rule " + name + " could not be applied to " + systemKey + ". System model " + systemModel
					+ " doesn't match rule model " + applicableModel + ".");
			return false;
		}

		return true;
	}
	
	/**
	 * Concrete rule should implement this method with the actual rule logic.
	 * 
	 * @param systemKey
	 * @throws CCProcessingServiceUnavailableException
	 */
	abstract protected void execute(String systemKey);
	
	@Override  
    public Object clone() throws CloneNotSupportedException {  
        return super.clone();  
    }  

}

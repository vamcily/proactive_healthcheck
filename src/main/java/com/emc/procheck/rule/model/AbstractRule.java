/**
 * 
 */
package com.emc.procheck.rule.model;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.emc.procheck.config.CommonHealthCheckConfig;
import com.emc.procheck.rule.model.RuleResult.RuleStatus;
import com.emc.procheck.storage.model.Event;
import com.emc.procheck.storage.service.EventService;
import com.emc.procheck.util.ApplicationContextUtil;

/**
 * @author Eric Wu
 *
 */
public abstract class AbstractRule implements IRule, Cloneable {

	private String id;
	private String name;
	private String uSerivce;
	private String stdVer;
	private String description;
	private String category;
	protected int impact;
	protected String applicableVersion;
	protected String applicableModel;
	protected RuleResult result;
	
	private HealthPacket basicPacketInfo;

	protected Event event;

	private final static Logger logger = LoggerFactory.getLogger(AbstractRule.class);

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStdVer() {
		return stdVer;
	}

	public void setStdVer(String stdVer) {
		this.stdVer = stdVer;
	}

	public String getuSerivce() {
		return uSerivce;
	}

	public void setuSerivce(String uSerivce) {
		this.uSerivce = uSerivce;
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
	public int getImpact() {
        return impact;
    }
	
	public void setImpact(int impact) {
		this.impact = impact;
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

		EventService service = (EventService) ApplicationContextUtil.getBean(EventService.BEANNAME);
		event = service.findBySystemKey(systemKey);
		if (event == null) {
			logger.error("Could not find the event object with systemKey " + systemKey);
			result.setStatus(RuleStatus.NOT_APPLICABLE);
			return;
		}

		// This is an example of common check whether rule is applicable on
		// target system.
		// It could be removed if not needed.
//		if (!checkIfApplicable(systemKey)) {
//			result.setStatus(RuleStatus.NOT_APPLICABLE);
//			return;
//		}

		result.setStartTime(new Date());
		result.setStatus(RuleStatus.RUNNING);

		basicPacketInfo = new HealthPacket();
		basicPacketInfo.setuService(uSerivce);
		basicPacketInfo.setStdVer(stdVer);
		basicPacketInfo.setSerialNo(event.getSerialNumber());
		basicPacketInfo.setSourceDataCreation(event.getTime());

		CommonHealthCheckConfig hcConfig = (CommonHealthCheckConfig) ApplicationContextUtil
				.getBean(CommonHealthCheckConfig.BEANNAME);
		basicPacketInfo.setPacketVer(hcConfig.getPacketVersion());

		execute(systemKey);

	}
	
	@Override
	public void checkWithoutTenant(String sn, String systemKey) {

		result = new RuleResult();
		result.setStartTime(new Date());
		result.setStatus(RuleStatus.RUNNING);

		HealthPacket packet = new HealthPacket();
		packet.setId(UUID.randomUUID().toString());
		packet.setCreated(new Date());
		packet.setuService(uSerivce);
		packet.setStdVer(stdVer);
		packet.setSerialNo(sn);

		CommonHealthCheckConfig hcConfig = (CommonHealthCheckConfig) ApplicationContextUtil
				.getBean(CommonHealthCheckConfig.BEANNAME);
		packet.setPacketVer(hcConfig.getPacketVersion());

		execute(systemKey);

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	private boolean checkIfApplicable(String systemKey) {
		if (event.getOeVersion() == null || event.getOeRevision() == null) {
			logger.warn("Version or revision is null for " + systemKey);
			return false;
		}

		if (event.getModel() == null) {
			logger.warn("Model is null for " + systemKey);
			return false;
		}

		String systemVersion = event.getOeVersion() + "." + event.getOeRevision();

		Pattern pattern = Pattern.compile(applicableVersion);
		Matcher matcher = pattern.matcher(systemVersion);

		if (!matcher.matches()) {
			logger.info("The rule " + name + " could not be applied to " + systemKey + ". System version "
					+ systemVersion + " doesn't match rule version " + applicableVersion + ".");
			return false;
		}

		pattern = Pattern.compile(applicableModel);
		String systemModel = event.getModel();
		matcher = pattern.matcher(systemModel);
		if (!matcher.matches()) {
			logger.info("The rule " + name + " could not be applied to " + systemKey + ". System model " + systemModel
					+ " doesn't match rule model " + applicableModel + ".");
			return false;
		}

		return true;
	}
	
	protected HealthPacket createHealthPacket() {
	    HealthPacket packet = new HealthPacket();
	    BeanUtils.copyProperties(basicPacketInfo, packet, HealthPacket.class);
	    packet.setId(UUID.randomUUID().toString());
        packet.setCreated(new Date());
        
        return packet;
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
	
	/**
	 * Get default impact. For example, use it when failing to calculate impact by formula.
	 * @return
	 */
	protected int getDefaultImpact(){
		return this.getImpact();
	}
	
	/**
	 * If the impact is calculated by comparing it with default impact.
	 * @param impact
	 * @return
	 */
	protected boolean isDefaultImapct(int impact){
		return this.getDefaultImpact() == impact;
	}


}

package com.emc.procheck.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils to access cc common resources.
 */
public class CommonUtils {
	
	final static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	final static Integer timeLen = 14; 

	/**
	 * Low first letter
	 * @param str
	 * @return
	 */
	public static String lowerFirstLetter(String str) {
		String firstL = str.substring(0, 1);
		return str.replaceFirst(firstL, firstL.toLowerCase());
	}
	
	/**
	 * Up first letter
	 * @param str
	 * @return
	 */
	public static String upperFirstLetter(String str) {
		String firstL = str.substring(0, 1);
		return str.replaceFirst(firstL, firstL.toUpperCase());
	}
	
	/**
	 * Construct systemKey using sn and capture time in format:
	 * 
	 * @param fileName, In format: 20151010_055656_FCNCH09727495D
	 * @return In format: FCNCH09727495D20151010055656-0
	 */
	public static String getSystemKeyFromFileName(String fileName){
		String systemKey = null;
		if (fileName != null) {
			int index = fileName.lastIndexOf("_");
			if (index != -1) {
				String timeStr = fileName.substring(0, index);
				timeStr = timeStr.replaceAll("_", "");
				String snStr = fileName.substring(index + 1);
				systemKey = snStr + timeStr + "-0";
			}else{
				logger.error("Fail to get systemKey because fileName is in wrong format: " + fileName);
			}
		}else{
			logger.error("Fail to get systemKey because fileName is null.");
		}
		return systemKey;
	}
	
	/**
	 * Parse sn from systemkey:
	 * 
	 * @param systemKey, In format: FCNCH09727495D20151010055656-0
	 * @return In format: FCNCH09727495D
	 */
	public static String getSnFromSystemKey(String systemKey){
		String sn = null;
		if (systemKey != null) {
			int index = systemKey.indexOf("-");
			if (index != -1) {
				String snTimeStr = systemKey.substring(0, index);
				sn = snTimeStr.substring(0, snTimeStr.length() - timeLen);
			}else{
				logger.error("Fail to get sn because systemKey is in wrong format: " + systemKey);
			}
		}else{
			logger.error("Fail to get sn because systemKey is null.");
		}
		return sn;
	}
	/**
	 * 
	 * @param fileName, In format: 20160205_215703_FNM00153900791_EMC-UEM-Telemetry.tar.gz
	 * @return In format: 20160205_215703_FNM00153900791
	 */
	public static String parseFileName(String fileName, String ccFileSuffix) {
		Pattern p = Pattern.compile("(\\d{8}_\\d{6})_([a-zA-Z0-9]+)" + ccFileSuffix);
		Matcher m = p.matcher(fileName);
		String sn = "unknown";
		String captureTimeStr = "unknown";
		Date captureTime = null;
		if (m.find()) {
			sn = m.group(2);
			captureTimeStr = m.group(1);
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
				formatter.setLenient(false);
				captureTime = formatter.parse(captureTimeStr);
			} catch (ParseException exc) {
				logger.error("Failed to parse " + captureTimeStr + ": " + exc.getMessage());
			}
		}
		if (sn.equalsIgnoreCase("unknown") || captureTime == null) {
			return null;
		}

		return captureTimeStr + "_" + sn;
	}
	
	/**
	 * 
	 * @param fileName, In format: 20151010_055656_FCNCH09727495D or 20160205_215703_FNM00153900791_EMC-UEM-Telemetry.tar.gz
	 * @return In format: 20160205_215703_FNM00153900791
	 */
	public static String getSnFromFileName(String fileName) {
		String sn = null;
		String[] strs = StringUtils.split(fileName, "_");
		if(strs != null && strs.length > 2){
			sn = strs[2];
		}
		return sn;
	}
	
	public static boolean isNoTenant(String tenant){
		return "NONE".equals(tenant);
	}

}

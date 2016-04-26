/**
 * 
 */
package com.emc.procheck.rule.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Eric Wu
 *
 */
@Component
public class RuleFactory {

	private final String RULE_CONFIG_DIR;
	private final String RULE_CONFIG_EXT = ".json";
	private final String RULE_PACKAGE;

	private final static Logger logger = LoggerFactory.getLogger(RuleFactory.class);

	private static List<IRule> rules = null;

	private static Object lockObj = new Object();

	@Autowired
	public RuleFactory(@Value("${rule.config.dir}") String configDir,
			@Value("${rule.config.package}") String configPack) {
		RULE_CONFIG_DIR = configDir;
		RULE_PACKAGE = configPack;
	}

	/**
	 * Only loading rules configuration once to avoid frequently IO reading.
	 * @return
	 */
	public List<IRule> getRules() {
		if (rules == null) {
			synchronized (lockObj) {
				if (rules == null) {
					createRules();
				}
			}
		}
		try {
			// Clone rules to healch check to avoid dirty data persisted.
			List<IRule> newRules = new ArrayList<IRule>();
			for (IRule r : rules) {
				IRule rule = null;
				if(r instanceof AbstractRule){
					rule = (IRule)((AbstractRule)r).clone();
				}else{
					throw new CloneNotSupportedException("Only support rules extend from AbstractRule.");
				}
				newRules.add(rule);
			}
			return newRules;
		} catch (CloneNotSupportedException e) {
			logger.error("Failed to get rules.", e);
			return null;
		}
	}

	/**
	 * rules only need to be initialized once.
	 */
	private void createRules() {
		logger.debug("Begin to initialize rules.");
		if (rules == null)
			rules = new ArrayList<IRule>();

		FilenameFilter fileNameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(RULE_CONFIG_EXT);
			}
		};

		URL ruleDir = getClass().getResource(RULE_CONFIG_DIR);
		if (ruleDir == null) {
			logger.error("Cannot load rule directory " + RULE_CONFIG_DIR);
		}

		File[] files;
		try {
			files = new File(ruleDir.toURI()).listFiles(fileNameFilter);
		} catch (URISyntaxException e) {
			logger.error("Failed to list rule config files from: " + ruleDir.toString(), e);
			return;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		for (File file : files) {
			logger.debug("Loading rule from: " + file.getAbsolutePath());
			String fileName = file.getName();
			int pos = fileName.lastIndexOf(".");
			if (pos > 0) {
				fileName = fileName.substring(0, pos);
			}

			String className = RULE_PACKAGE + "." + fileName;
			InputStream in = null;
			try {
				Class<? extends IRule> clazz = Class.forName(className).asSubclass(IRule.class);
				in = new FileInputStream(file);

				IRule rule = objectMapper.readValue(in, clazz);
				rules.add(rule);
			} catch (ClassNotFoundException e) {
				logger.error("Cannot find class " + className, e);
			} catch (FileNotFoundException e) {
				logger.error("Cannot find file " + file.getPath(), e);
			} catch (JsonParseException e) {
				logger.error("Fail to parse rule config file " + file.getName(), e);
			} catch (JsonMappingException e) {
				logger.error("Fail to parse rule config file " + file.getName(), e);
			} catch (IOException e) {
				logger.error("Fail to load rule config file " + file.getName(), e);
			} finally {
				IOUtils.closeQuietly(in);
			}

		}

		logger.info("Rules[" + rules.size() + "] have been intiialized.");
	}

}

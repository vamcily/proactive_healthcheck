package com.emc.procheck.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Micro Service (App) Configuration, populated from pom.xml file
 */

@Configuration
public class AppConfig 
{
	@Value("${app.name:}")
    private String name;
    
	@Value("${app.version:}")
    private String version;
    
	@Value("${app.description:}")
    private String description;
    
	@Value("${app.build.date:}")
    private String buildDate;

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getDescription() {
		return description;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public String getConfig() {
		return "name=" + name + ", version=" + version
				+ ", description=" + description + ", buildDate=" + buildDate;
	}
    
}

package com.emc.procheck.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Jackon Json Customization
 */

@Configuration
public class JsonConfig 
{
    /**
     * Json serialization customization to avoid serializing NULL values 
     */
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.failOnUnknownProperties(false);
        return builder;
	}
}

package com.emc.procheck.config;

import java.nio.charset.Charset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig 
{
    @Bean
    public RestTemplate restClient() {
        RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));         
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());    
        
        return restTemplate;
    }
}

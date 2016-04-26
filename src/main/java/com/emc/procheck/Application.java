package com.emc.procheck;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.netflix.servo.ServoMetricsAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Start point of the project.
 * 
 * @author Eric Wu
 *
 */
@ComponentScan("com.emc.procheck")
@EnableJpaRepositories("com.emc.procheck")
@EntityScan("com.emc.procheck")
@EnableConfigurationProperties
@SpringBootApplication(exclude = { FlywayAutoConfiguration.class, ServoMetricsAutoConfiguration.class })
public class Application implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${spring.jackson.date-format}")
	private String jacksonDateFormat;

	public static void main(String[] args) {
		logger.info("Health check service is being started.");
		SpringApplication app = new SpringApplication(Application.class);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		setJacksonDateFormat();
	}

	private void setJacksonDateFormat() {
		// There is a bug in Spring boot that spring.jackson.date-format does
		// not work. Do it ourselves.
		if (StringUtils.isNotEmpty(jacksonDateFormat)) {
			try {
				objectMapper.setDateFormat(new SimpleDateFormat(jacksonDateFormat));
				logger.info("Set Jackson data format to be [" + jacksonDateFormat + "].");
			} catch (Exception e) {
				logger.error("Fail to set Jackson data format: " + e.getMessage(), e);
			}
		}
	}

}

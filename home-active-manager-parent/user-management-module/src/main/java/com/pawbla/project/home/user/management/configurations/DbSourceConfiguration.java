package com.pawbla.project.home.user.management.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DbSourceConfiguration {
    @Autowired
    Environment environment;

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Bean
    public DataSource dataSource() {
        logger.info("Create mySQL databse connection");
        logger.debug("Configuration parameters:" +
                " driver " + environment.getProperty("spring.datasource.driver-class-name") +
                " url " + environment.getProperty("spring.datasource.url") +
                " username " + environment.getProperty("spring.datasource.username"));
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
        driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return driverManagerDataSource;
    }
}

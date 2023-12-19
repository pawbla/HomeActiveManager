package com.pawbla.project.home.history.module.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Profile({"dev", "test"})
public class DbSourceConfigurationDev {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Bean
    public DataSource dataSource() {
        logger.info("Create embedded database connection - for tests.");
        return new EmbeddedDatabaseBuilder()
                .setName("testDB;MODE=MySQL")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("database/schema.sql")
                .addScript("database/data.sql")
                .build();
    }
}

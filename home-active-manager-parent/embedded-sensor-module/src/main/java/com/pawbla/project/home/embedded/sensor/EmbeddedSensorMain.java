package com.pawbla.project.home.embedded.sensor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class EmbeddedSensorMain {
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public static void main( String[] args ) {
        SpringApplication.run(EmbeddedSensorMain.class, args);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Application is shutting down");
    }
}

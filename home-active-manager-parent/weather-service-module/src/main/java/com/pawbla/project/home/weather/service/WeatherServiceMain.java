package com.pawbla.project.home.weather.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class WeatherServiceMain {

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public static void main( String[] args ) {
        SpringApplication.run(WeatherServiceMain.class, args);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Application is shutting down");
    }
}

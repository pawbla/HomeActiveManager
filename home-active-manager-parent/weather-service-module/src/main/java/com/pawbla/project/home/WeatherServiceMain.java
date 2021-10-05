package com.pawbla.project.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherServiceMain {

    private static final Logger logger = LogManager.getLogger(WeatherServiceMain.class);

    public static void main( String[] args ) {
        SpringApplication.run(WeatherServiceMain.class, args);
    }
}

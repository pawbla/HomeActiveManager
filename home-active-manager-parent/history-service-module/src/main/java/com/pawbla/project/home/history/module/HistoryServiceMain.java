package com.pawbla.project.home.history.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableFeignClients
public class HistoryServiceMain {

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public static void main( String[] args ) {
        SpringApplication.run(HistoryServiceMain.class, args);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Application is shutting down");
    }
}

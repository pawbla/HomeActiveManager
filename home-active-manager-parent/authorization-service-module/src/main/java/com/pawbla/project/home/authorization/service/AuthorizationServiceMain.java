package com.pawbla.project.home.authorization.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.annotation.PreDestroy;

@EnableAuthorizationServer
@SpringBootApplication
public class AuthorizationServiceMain {

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public static void main( String[] args ) {
        SpringApplication.run(AuthorizationServiceMain.class, args);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Application is shutting down");
    }
}

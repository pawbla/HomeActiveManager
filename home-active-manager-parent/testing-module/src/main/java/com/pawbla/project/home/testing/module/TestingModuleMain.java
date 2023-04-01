package com.pawbla.project.home.testing.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.annotation.PreDestroy;
import java.awt.*;

@SpringBootApplication
public class TestingModuleMain {
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public static void main( String[] args ) {
        ApplicationContext context =  new SpringApplicationBuilder(TestingModuleMain.class).headless(false).run(args);
        EventQueue.invokeLater(() -> {

            Frame ex = context.getBean(Frame.class)  ;
            ex.setVisible(true);
        });
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Application is shutting down");
    }
}

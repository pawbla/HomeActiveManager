package com.pawbla.project.home.application.monitoring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("prod")
public class CommandExecutorLinux implements CommandExecutor {
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public int execute(String cmd) {
        log.info("Execute command {}", cmd);
        Process process = null;
        int exitCode = -9;
        try {
            process = Runtime.getRuntime().exec(cmd);
            exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            log.warn("Exception for executed command {}", e.getMessage());
        } finally {
            process.destroy();
        }
        log.info("Exit code {}", exitCode);
        return exitCode;
    }
}

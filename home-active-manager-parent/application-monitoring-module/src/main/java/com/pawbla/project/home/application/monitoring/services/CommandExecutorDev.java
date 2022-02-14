package com.pawbla.project.home.application.monitoring.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class CommandExecutorDev implements CommandExecutor {

    public int execute(String cmd) {
        return 0;
    }
}

package com.pawbla.project.home.testing.module.handlers.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("monitoringModuleProcessHandler")
public class MonitoringModuleProcessHandler extends AbstractProcessHandler {

    public MonitoringModuleProcessHandler(@Value("${monitoring.module.path}") final String pathToApp,
                                          @Value("${monitoring.module.params}") final String[] params) {
        super(pathToApp, Arrays.asList(params));
    }
}

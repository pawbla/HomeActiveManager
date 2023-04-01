package com.pawbla.project.home.testing.module.handlers.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("embeddedSensorModuleProcessHandler")
public class EmbeddedSensorProcessHandler extends AbstractProcessHandler {
    public EmbeddedSensorProcessHandler(@Value("${embedded.sensor.module.path}") String pathToApp,
                                        @Value("${embedded.sensor.module.params}") List<String> params) {
        super(pathToApp, params);
    }
}

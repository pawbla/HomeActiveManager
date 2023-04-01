package com.pawbla.project.home.testing.module.handlers.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("weatherServiceProcessHandler")
public class WeatherServiceProcessHandler extends AbstractProcessHandler {
    public WeatherServiceProcessHandler(@Value("${weather.service.module.path}") final String pathToApp,
                                        @Value("${weather.service.module.params}") final List<String> params) {
        super(pathToApp, params);
    }
}

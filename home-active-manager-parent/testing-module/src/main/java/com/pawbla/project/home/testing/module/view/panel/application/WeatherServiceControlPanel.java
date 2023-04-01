package com.pawbla.project.home.testing.module.view.panel.application;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("weatherServiceControlPanel")
public class WeatherServiceControlPanel extends AbstractAppPanel {

    private static final String TITLE = "Weather Service Module";

    public WeatherServiceControlPanel(@Qualifier("weatherServiceProcessHandler") final ProcessHandler processHandler) {
        super(TITLE, processHandler);
    }
}

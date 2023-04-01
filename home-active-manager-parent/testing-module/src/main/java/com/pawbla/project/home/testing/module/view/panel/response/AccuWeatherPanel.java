package com.pawbla.project.home.testing.module.view.panel.response;

import org.springframework.stereotype.Component;

@Component("accuWeatherPanel")
public class AccuWeatherPanel extends ScenarioPanel {

    private static final String TITLE = "AccuWeather";

    public AccuWeatherPanel() {
        super(TITLE);
    }
}

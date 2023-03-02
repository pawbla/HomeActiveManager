package com.pawbla.project.home.testing.module.view.panel.response;

import org.springframework.stereotype.Component;

@Component("airLyInstPanel")
public class AirLyInstPanel extends ScenarioPanel {

    private static final String TITLE = "AirLyInstallation";

    public AirLyInstPanel() {
        super(TITLE);
    }
}

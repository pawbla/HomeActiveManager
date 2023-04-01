package com.pawbla.project.home.testing.module.view.panel.response;

import org.springframework.stereotype.Component;

@Component("airLyPanel")
public class AirLyPanel extends ScenarioPanel {

    private static final String TITLE = "AirLy";

    public AirLyPanel() {
        super(TITLE);
    }
}

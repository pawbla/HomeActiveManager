package com.pawbla.project.home.testing.module.view.panel.response;

import org.springframework.stereotype.Component;

@Component("sunRiseSetPanel")
public class SunRiseSetPanel extends ScenarioPanel {

    private static final String TITLE = "SunRiseSet";

    public SunRiseSetPanel() {
        super(TITLE);
    }
}

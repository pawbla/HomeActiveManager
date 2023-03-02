package com.pawbla.project.home.testing.module.view.panel.response;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component("respCfgPanel")
public class ResponseConfigurationPanel extends JPanel {
    public ResponseConfigurationPanel(@Qualifier("sunRiseSetPanel") ScenarioPanel internalPanel, @Qualifier("airLyPanel") ScenarioPanel airLyPanel,
                                      @Qualifier("accuWeatherPanel") ScenarioPanel accuWeatherPanel, @Qualifier("airLyInstPanel") ScenarioPanel airLyInstPanel) {
        setSize(150, 360);
        setLayout(new GridLayout(4,1));
        add(internalPanel);
        add(airLyPanel);
        add(accuWeatherPanel);
        add(airLyInstPanel);
    }
}

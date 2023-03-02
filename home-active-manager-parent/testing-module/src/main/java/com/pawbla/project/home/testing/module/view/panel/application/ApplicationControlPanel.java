package com.pawbla.project.home.testing.module.view.panel.application;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component("appControlPanel")
public class ApplicationControlPanel extends JPanel {

    private static final String TITLE = "Application Control Panel";

    public ApplicationControlPanel(@Qualifier("monitoringModuleControlPanel") JPanel monitoringModulePanel,
                                   @Qualifier("embeddedSensorModuleControlPanel") JPanel embeddedSensorModulePanel,
                                   @Qualifier("weatherServiceControlPanel") JPanel weatherServicePanel,
                                   @Qualifier("reactAppControlPanel") JPanel reactAppPanel) {
        setBorder(BorderFactory.createTitledBorder(TITLE));
        setLayout(new GridLayout(4,1));
        add(weatherServicePanel);
        add(embeddedSensorModulePanel);
        add(monitoringModulePanel);
        add(reactAppPanel);
    }
}

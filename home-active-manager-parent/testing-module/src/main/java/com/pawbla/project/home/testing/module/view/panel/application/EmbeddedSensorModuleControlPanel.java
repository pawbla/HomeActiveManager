package com.pawbla.project.home.testing.module.view.panel.application;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("embeddedSensorModuleControlPanel")
public class EmbeddedSensorModuleControlPanel extends AbstractAppPanel {

    private static final String TITLE = "Embedded Sensor Module";

    public EmbeddedSensorModuleControlPanel(@Qualifier("embeddedSensorModuleProcessHandler") final ProcessHandler processHandler) {
        super(TITLE, processHandler);
    }
}

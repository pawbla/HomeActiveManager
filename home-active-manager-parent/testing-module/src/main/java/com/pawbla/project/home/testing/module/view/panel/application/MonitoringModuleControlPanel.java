package com.pawbla.project.home.testing.module.view.panel.application;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("monitoringModuleControlPanel")
public class MonitoringModuleControlPanel extends AbstractAppPanel {

    private static final String TITLE = "Monitoring Module";

    public MonitoringModuleControlPanel(@Qualifier("monitoringModuleProcessHandler") final ProcessHandler processHandler) {
        super(TITLE, processHandler);
    }
}

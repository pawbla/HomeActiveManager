package com.pawbla.project.home.testing.module.view.panel.application;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("reactAppControlPanel")
public class ReactAppControlPanel extends AbstractAppPanel {

    private static final String TITLE = "Frontend";

    public ReactAppControlPanel(@Qualifier("reactAppProcessHandler") final ProcessHandler processHandler) {
        super(TITLE, processHandler);
    }
}

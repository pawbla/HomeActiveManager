package com.pawbla.project.home.testing.module.view.items;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;

public class StartButton extends AbstractButton {

    private final ProcessHandler processHandler;

    public StartButton(ProcessHandler processHandler) {
        super("Start");
        this.processHandler = processHandler;
    }

    @Override
    protected void executeAction() {
        processHandler.start();
    }
}

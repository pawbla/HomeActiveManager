package com.pawbla.project.home.testing.module.view.items;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;

public class StopButton extends AbstractButton {

    private final ProcessHandler processHandler;

    public StopButton(final ProcessHandler processHandler) {
        super("Stop");
        this.processHandler = processHandler;
    }

    @Override
    protected void executeAction() {
        processHandler.stop();
    }
}

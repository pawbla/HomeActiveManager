package com.pawbla.project.home.testing.module.view.items;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;

import javax.swing.*;

public class ProcessControlLabel extends JLabel {

    public ProcessControlLabel(ProcessHandler processHandler) {
        setHorizontalAlignment(SwingConstants.CENTER);
        setText("OFF");
        Timer timer = new Timer(500, e -> {
            if(processHandler.isAlive()) {
                setText("ON");
            } else {
                setText("OFF");
            }
        });
        timer.start();
    }


}

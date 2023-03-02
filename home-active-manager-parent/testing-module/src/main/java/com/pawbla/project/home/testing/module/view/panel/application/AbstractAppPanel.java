package com.pawbla.project.home.testing.module.view.panel.application;

import com.pawbla.project.home.testing.module.handlers.app.ProcessHandler;
import com.pawbla.project.home.testing.module.view.items.ProcessControlLabel;
import com.pawbla.project.home.testing.module.view.items.StartButton;
import com.pawbla.project.home.testing.module.view.items.StopButton;

import javax.swing.*;
import java.awt.*;

public class AbstractAppPanel extends JPanel {
    public AbstractAppPanel(final String title, final ProcessHandler processHandler) {
        setLayout(new BorderLayout());
        add(new JLabel(title), BorderLayout.NORTH);
        add(new StartButton(processHandler), BorderLayout.WEST);
        add(new ProcessControlLabel(processHandler), BorderLayout.CENTER);
        add(new StopButton(processHandler), BorderLayout.EAST);
        setPreferredSize(new Dimension(200, 1));
    }
}

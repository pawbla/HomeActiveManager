package com.pawbla.project.home.testing.module.view;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class Frame extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 360;
    private static final String TITLE = "EndToEnd Testing framework";

    public Frame(@Qualifier("respCfgPanel") JPanel responseConfigurationPanel, @Qualifier("appControlPanel") JPanel appControlPanel) {
        setSize(WIDTH,HEIGHT);
        setLocationByPlatform(true);
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(responseConfigurationPanel, BorderLayout.CENTER);
        add(appControlPanel, BorderLayout.EAST);
    }
}

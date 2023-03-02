package com.pawbla.project.home.testing.module.view.items;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractButton extends JButton {
    public AbstractButton(String title) {
        setText(title);
        addActionListener(getButtonAction());
    }

    protected abstract void executeAction();

    private ActionListener getButtonAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAction();
            }
        };
    }
}

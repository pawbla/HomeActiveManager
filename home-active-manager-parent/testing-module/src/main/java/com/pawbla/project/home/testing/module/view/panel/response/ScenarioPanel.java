package com.pawbla.project.home.testing.module.view.panel.response;

import javax.swing.*;
import java.util.List;

public abstract class ScenarioPanel extends JPanel {

    private final JComboBox<String> comboBox;

    public ScenarioPanel(String title) {
        setBorder(BorderFactory.createTitledBorder(title));
        comboBox = new JComboBox<>();
    }

    public void createItems(List<String> items) {
        items.stream().forEach(comboBox::addItem);
        add(comboBox);
    }

    public String getSelection() {
        return (String) comboBox.getSelectedItem();
    }
}

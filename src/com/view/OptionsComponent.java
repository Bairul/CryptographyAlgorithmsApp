package com.view;

import com.controller.AlgorithmInfo;
import com.model.enums.Algorithm;
import com.model.AlgorithmItem;
import com.model.MyUtil;
import com.model.enums.Option;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class OptionsComponent extends JComponent {
    private final JComboBox<AlgorithmItem> optionsComboBox;
    private final JPanel radioPanel;
    private final ButtonGroup radioGroup;
    private final OutputItems outputItems;

    private final Map<Algorithm, Option[]> optionsMap = AlgorithmInfo.getAlgorithmOptions();
    private final Map<Option, JRadioButton> optionsButtonsMap;

    private Algorithm selectedAlgorithm;
    private Option selectedOption;

    public OptionsComponent(final JTextArea descriptionText, final JPanel radioPanel, OutputItems outputItems) {
        setLayout(new FlowLayout());
        this.radioPanel = radioPanel;
        this.outputItems = outputItems;
        radioGroup = new ButtonGroup();

        optionsButtonsMap = new HashMap<>();
        for (Option opt : Option.values()) {
            JRadioButton radioButton = new JRadioButton(opt.name());
            radioButton.addActionListener(e -> selectedOption = opt);
            optionsButtonsMap.put(opt, radioButton);
        }

        // dropdown
        optionsComboBox = new JComboBox<>(MyUtil.getAllAlgorithmNames());
        optionsComboBox.addActionListener(e -> {
            AlgorithmItem selected = (AlgorithmItem) optionsComboBox.getSelectedItem();

            assert selected != null;
            if (selected.getAlgorithm() != selectedAlgorithm) {
                outputItems.outputTextArea.setText("");
                outputItems.exportButton.setEnabled(false);
                outputItems.swapOutputInputButton.setEnabled(false);
                outputItems.swapOutputPasswordButton.setEnabled(false);
                selectedAlgorithm = selected.getAlgorithm();
            }

            descriptionText.setText(selected.getDescription());
            updateRadioPanel();
        });

        // default to first dropdown
        selectedAlgorithm = Algorithm.CAESAR_CIPHER;
        descriptionText.setText(optionsComboBox.getItemAt(0).getDescription());

        // Panel for dropdown and description
        add(optionsComboBox);
        updateRadioPanel();
    }

    private void updateRadioPanel() {
        radioPanel.removeAll();        // Clear existing radio buttons
        radioGroup.clearSelection();   // Clear selected button, if any

        // Add new radio buttons for the selected option set
        for (Option option : optionsMap.get(selectedAlgorithm)) {
            JRadioButton radioButton = optionsButtonsMap.get(option);
            radioGroup.add(radioButton);
            radioPanel.add(radioButton);
        }

        // default to the first option
        selectedOption = optionsMap.get(selectedAlgorithm)[0];
        optionsButtonsMap.get(selectedOption).setSelected(true);

        radioPanel.revalidate();  // Refresh the panel
        radioPanel.repaint();
    }

    public Algorithm getSelectedAlgorithm() {
        return selectedAlgorithm;
    }
    public Option getSelectedOption() {
        return selectedOption;
    }
}

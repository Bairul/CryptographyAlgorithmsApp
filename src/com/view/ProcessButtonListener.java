package com.view;

import com.controller.CryptoProcessor;
import com.model.Algorithm;
import com.model.Option;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessButtonListener implements ActionListener {
    private final JTextArea messageTextField;
    private final JTextArea keyTextField;
    private final OptionsComponent optionsDropdown;
    private final JTextArea outputTextArea;
    private final JButton exportButton;
    private final JButton swapButton;

    public ProcessButtonListener(JTextArea messageTextField, JTextArea keyTextField, OptionsComponent optionsDropdown, JTextArea outputTextArea, JButton exportButton, JButton swapButton) {
        this.messageTextField = messageTextField;
        this.keyTextField = keyTextField;
        this.optionsDropdown = optionsDropdown;
        this.outputTextArea = outputTextArea;
        this.exportButton = exportButton;
        this.swapButton = swapButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String messageText = messageTextField.getText();
        String keyText = keyTextField.getText();

        if (messageText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (keyText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Process input (this can be customized)
        String outputText = CryptoProcessor.process(optionsDropdown.getSelectedAlgorithm(), optionsDropdown.getSelectedOption(), keyText, messageText);

        // Display output and enable export button
        outputTextArea.setText(outputText);
        exportButton.setEnabled(true);
        swapButton.setEnabled(true);
    }
}

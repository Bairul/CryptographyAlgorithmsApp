package com.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.view.Display.NO_FILE_LABEL;

public class FileUploadButtonListener implements ActionListener {
    private final JTextArea inputTextField;
    private final JLabel fileLabel;
    private final JButton clearButton;

    public FileUploadButtonListener(final JTextArea inputTextField, final JLabel fileLabel, final JButton clearButton) {
        this.inputTextField = inputTextField;
        this.fileLabel = fileLabel;
        this.clearButton = clearButton;

        clearButton.setEnabled(false); // Disabled initially
        clearButton.setVisible(false);
        clearButton.addActionListener(e -> clearFile());
    }

    private void clearFile() {
        inputTextField.setText("");
        inputTextField.setEditable(true);  // Re-enable editing
        fileLabel.setText(NO_FILE_LABEL);
        clearButton.setEnabled(false);     // Disable the clear button after clearing
        clearButton.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        // Set a filter for .txt files only
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        fileChooser.setFileFilter(txtFilter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Verify that the selected file is a .txt file
            if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                JOptionPane.showMessageDialog(null, "Please select a valid .txt file", "Invalid File", JOptionPane.ERROR_MESSAGE);
                return;
            }

            fileLabel.setText("Selected file: " + selectedFile.getName());

            // Load file content into the inputTextField
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                inputTextField.setText(content.toString());
                inputTextField.setEditable(false);
                clearButton.setEnabled(true);
                clearButton.setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to load file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            fileLabel.setText(NO_FILE_LABEL);
        }
    }
}

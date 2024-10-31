package com.view;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    public static final String NO_FILE_LABEL = "No .txt selected";

    private final JTextArea messageTextField;
    private final JTextArea keyTextField;
    private final JTextArea outputTextArea;
    private final OptionsComponent dropdownPanel;
    private final JPanel radioPanel;
    private final JLabel dropdownDescription;

    public Display() {
        setTitle("Cryptography");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        messageTextField = new JTextArea();
        keyTextField = new JTextArea();
        outputTextArea = new JTextArea();
        dropdownDescription = new JLabel();
        radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
        dropdownPanel = new OptionsComponent(dropdownDescription, radioPanel);

        // Add panels to frame
        add(setUpInputPanel(), BorderLayout.NORTH);
        add(setUpOutputPanel(), BorderLayout.CENTER);
        add(setUpBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel setUpBottomPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton processButton = new JButton("Process Input");
        JButton exportButton = new JButton("Export Output as .txt");
        JButton swapButton = new JButton("Set Output as Input");
        exportButton.setEnabled(false);
        swapButton.setEnabled(false);

        processButton.addActionListener(new ProcessButtonListener(messageTextField, keyTextField, dropdownPanel, outputTextArea, exportButton, swapButton));
        exportButton.addActionListener(new ExportFileButtonListener(outputTextArea));
        swapButton.addActionListener(e -> {
            messageTextField.setText(outputTextArea.getText());
            outputTextArea.setText("");
            exportButton.setEnabled(false);
            swapButton.setEnabled(false);
        });

        buttonPanel.add(processButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(swapButton);

        return buttonPanel;
    }

    private JPanel setUpOutputPanel() {
        JPanel outputPanel = new JPanel(new BorderLayout());

        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        JLabel outputLabel = new JLabel("Output:");
        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        outputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return outputPanel;
    }

    private JPanel setUpInputPanel() {
        messageTextField.setLineWrap(false);
        messageTextField.setWrapStyleWord(false);
        keyTextField.setLineWrap(false);
        keyTextField.setWrapStyleWord(false);

        JScrollPane messageScrollPane = new JScrollPane(messageTextField);
        JScrollPane keyScrollPane = new JScrollPane(keyTextField);
        Dimension size = new Dimension(400, 200);
        messageScrollPane.setPreferredSize(size);
        messageScrollPane.setMinimumSize(size);
        messageScrollPane.setMaximumSize(size);
        keyScrollPane.setPreferredSize(size);
        keyScrollPane.setMinimumSize(size);
        keyScrollPane.setMaximumSize(size);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Padding around components

        // getting the plain text message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        JButton messageFileUploadButton = new JButton("Upload Message");
        JButton messageClearButton = new JButton("X");
        JLabel messageFileLabel = new JLabel(NO_FILE_LABEL);
        messageFileUploadButton.addActionListener(new FileUploadButtonListener(messageTextField, messageFileLabel, messageClearButton));

        JPanel messageFileUploadPanel = new JPanel(new FlowLayout());
        messageFileUploadPanel.add(messageFileUploadButton);
        messageFileUploadPanel.add(messageFileLabel);
        messageFileUploadPanel.add(messageClearButton);

        JLabel messageInstructionLabel = new JLabel("Input:");
        messageInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(messageInstructionLabel, BorderLayout.NORTH);
        messagePanel.add(messageScrollPane, BorderLayout.CENTER);
        messagePanel.add(messageFileUploadPanel, BorderLayout.SOUTH);


        // getting the key/password panel
        JPanel keyPanel = new JPanel(new BorderLayout());
        JButton keyFileUploadButton = new JButton("Upload Password");
        JButton keyClearButton = new JButton("X");
        JLabel keyFileLabel = new JLabel(NO_FILE_LABEL);
        keyFileUploadButton.addActionListener(new FileUploadButtonListener(keyTextField, keyFileLabel, keyClearButton));

        JPanel keyFileUploadPanel = new JPanel(new FlowLayout());
        keyFileUploadPanel.add(keyFileUploadButton);
        keyFileUploadPanel.add(keyFileLabel);
        keyFileUploadPanel.add(keyClearButton);

        JLabel keyInstructionLabel = new JLabel("Password:");
        keyInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        keyPanel.add(keyInstructionLabel, BorderLayout.NORTH);
        keyPanel.add(keyScrollPane, BorderLayout.CENTER);
        keyPanel.add(keyFileUploadPanel, BorderLayout.SOUTH);

        dropdownDescription.setSize(new Dimension(200, 50));
        dropdownDescription.setPreferredSize(new Dimension(200, 50));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(messagePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(keyPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(dropdownPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(dropdownDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(radioPanel, gbc);

        return inputPanel;
    }
}

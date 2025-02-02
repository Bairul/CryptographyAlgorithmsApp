package com.view;

import javax.swing.*;

public class OutputItems {

    final JTextArea outputTextArea;
    final JButton exportButton;
    final JButton swapOutputInputButton;
    final JButton swapOutputPasswordButton;

    public OutputItems(JTextArea outputTextArea, JButton exportButton, JButton swapOutputInputButton, JButton swapOutputPasswordButton) {
        this.outputTextArea = outputTextArea;
        this.exportButton = exportButton;
        this.swapOutputInputButton = swapOutputInputButton;
        this.swapOutputPasswordButton = swapOutputPasswordButton;
    }
}

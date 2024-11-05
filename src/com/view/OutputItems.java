package com.view;

import javax.swing.*;

public class OutputItems {

    final JTextArea outputTextArea;
    final JButton exportButton;
    final JButton swapButton;

    public OutputItems(JTextArea outputTextArea, JButton exportButton, JButton swapButton) {
        this.outputTextArea = outputTextArea;
        this.exportButton = exportButton;
        this.swapButton = swapButton;
    }
}

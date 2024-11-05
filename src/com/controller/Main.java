package com.controller;

import com.view.Display;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Display::new);
    }
}

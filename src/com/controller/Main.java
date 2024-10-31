package com.controller;

import com.view.Display;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        final Algorithm alg = Algorithm.CAESAR_CIPHER;
//        final String key = "7";
//        final String plainText = "If he had anything confidential to say, he wrote it in cipher, that is, by so changing the order of the letters of the alphabet, that not a word could be made out.";
//
//        final String cryptogram = Encryptor.Encrypt(alg, key, plainText);
//        System.out.println(cryptogram);
        SwingUtilities.invokeLater(Display::new);
    }
}

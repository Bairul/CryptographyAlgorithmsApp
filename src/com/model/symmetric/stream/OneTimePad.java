package com.model.symmetric.stream;

public class OneTimePad {
    public static String encrypt(final int[] keys, final String plainText) {
        StringBuilder cipherText = new StringBuilder(plainText.length());

        for (int i = 0; i < plainText.length(); i++) {
            cipherText.append((char) ((plainText.charAt(i) + 1) ^ keys[i]));
        }

        return cipherText.toString();
    }

    public static String decrypt(final int[] keys, final String cipherText) {
        StringBuilder decryptedText = new StringBuilder(cipherText.length());

        for (int i = 0; i < cipherText.length(); i++) {
            decryptedText.append((char) ((cipherText.charAt(i) ^ keys[i]) - 1));
        }

        return decryptedText.toString();
    }
}

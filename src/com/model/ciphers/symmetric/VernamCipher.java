package com.model.ciphers.symmetric;

public class VernamCipher {
    public static String encrypt(final int[] keys, final String plainText) {
        StringBuilder cipherText = new StringBuilder(plainText.length());

        for (int i = 0; i < plainText.length(); i++) {
            cipherText.append((char) ((plainText.charAt(i) + 1) ^ keys[i % keys.length]));
        }

        return cipherText.toString();
    }

    public static String decrypt(final int[] keys, final String cipherText) {
        StringBuilder decryptedText = new StringBuilder(cipherText.length());

        for (int i = 0; i < cipherText.length(); i++) {
            decryptedText.append((char) ((cipherText.charAt(i) ^ keys[i % keys.length]) - 1));
        }

        return decryptedText.toString();
    }
}

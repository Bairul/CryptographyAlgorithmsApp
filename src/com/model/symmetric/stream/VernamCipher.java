package com.model.symmetric.stream;

public class VernamCipher {
    public static String encrypt(final int[] keys, final String plainText) {
        System.out.println(keys.length == plainText.length());
        StringBuilder cipherText = new StringBuilder(plainText.length());

        for (int i = 0; i < plainText.length(); i++) {
            cipherText.append((char) ((plainText.charAt(i) + 1) ^ keys[i % keys.length]));
        }

        return cipherText.toString();
    }

    public static String decrypt(final int[] keys, final String cipherText) {
        System.out.println(keys.length == cipherText.length());
        StringBuilder decryptedText = new StringBuilder(cipherText.length());

        for (int i = 0; i < cipherText.length(); i++) {
            decryptedText.append((char) ((cipherText.charAt(i) ^ keys[i % keys.length]) - 1));
        }

        return decryptedText.toString();
    }
}

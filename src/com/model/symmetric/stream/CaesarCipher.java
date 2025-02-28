package com.model.symmetric.stream;

public final class CaesarCipher {
    public static String encrypt(final int key, final String plainText) {
        StringBuilder cipherText = new StringBuilder(plainText.length());

        for (int i = 0; i < plainText.length(); i++) {
            int currentChar = plainText.charAt(i);

            if (currentChar >= 'a' && currentChar <= 'z') {
                // Lowercase
                currentChar = (currentChar - 'a' + key) % 26 + 'a';
            } else if (currentChar >= 'A' && currentChar <= 'Z') {
                // Uppercase
                currentChar = (currentChar - 'A' + key) % 26 + 'A';
            }

            cipherText.append((char) currentChar);
        }

        return cipherText.toString();
    }

    public static String decrypt(final int key, final String cipherText) {
        StringBuilder decryptedText = new StringBuilder(cipherText.length());

        for (int i = 0; i < cipherText.length(); i++) {
            int currentChar = cipherText.charAt(i);

            if (currentChar >= 'a' && currentChar <= 'z') {
                // Lowercase
                currentChar = (currentChar - 'z' - key) % 26 + 'z';
            } else if (currentChar >= 'A' && currentChar <= 'Z') {
                // Uppercase
                currentChar = (currentChar - 'Z' - key) % 26 + 'Z';
            }

            decryptedText.append((char) currentChar);
        }

        return decryptedText.toString();
    }
}

package com.controller;

import com.model.Algorithm;
import com.model.Option;
import com.model.ciphers.symmetric.CaesarCipher;
import com.model.ciphers.symmetric.OneTimePad;
import com.model.ciphers.symmetric.VernamCipher;
import com.model.ciphers.symmetric.VigenereCipher;

public final class CryptoProcessor {
    public static String process(final Algorithm alg, final Option opt, final String key, final String input) {
        return switch (opt) {
            case MAC -> "Mac'ed";
            case ENCRYPT -> encrypt(alg, key, input);
            case DECRYPT -> decrypt(alg, key, input);
            case GEN_KEYPAIR -> "keypair generated";
            case ENCRYPT_EC -> "encrypted using elliptic curve";
            case DECRYPT_EC -> "decrypted using elliptic curve";
            case SIGN -> "signed";
            case VERIFY -> "verified";
        };
    }
    private static String encrypt(final Algorithm alg, final String key, final String plainText) {
        String cryptogram = null;

        switch (alg) {
            case CAESAR_CIPHER -> cryptogram = CaesarCipher.encrypt(convertToNumericKey(key), plainText);
            case VIGENERE_CIPHER -> cryptogram = VigenereCipher.encrypt(plainText);
            case VERNAM_CIPHER -> cryptogram = VernamCipher.encrypt(plainText);
            case ONE_TIME_PAD -> cryptogram = OneTimePad.encrypt(plainText);
        }

        if (cryptogram == null) {
            cryptogram = "![Error in Encryption]!";
        }

        return cryptogram;
    }

    private static String decrypt(final Algorithm alg, final String key, final String cipherText) {
        String message = null;

        switch (alg) {
            case CAESAR_CIPHER -> message = CaesarCipher.decrypt(convertToNumericKey(key), cipherText);
            case VIGENERE_CIPHER -> message = VigenereCipher.encrypt(cipherText);
            case VERNAM_CIPHER -> message = VernamCipher.encrypt(cipherText);
            case ONE_TIME_PAD -> message = OneTimePad.encrypt(cipherText);
        }

        if (message == null) {
            message = "[Error in Decryption]";
        }

        return message;
    }

    private static int convertToNumericKey(final String key) {
        int numKey;
        try {
            numKey = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            numKey = 0;
        }

        return numKey;
    }
}

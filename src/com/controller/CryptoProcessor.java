package com.controller;

import com.model.enums.Algorithm;
import com.model.enums.Option;
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
            case CAESAR_CIPHER -> cryptogram = tryConvertToNumericKey(key) ? CaesarCipher.encrypt(convertToNumericKey(key), plainText) : null;
            case VIGENERE_CIPHER -> cryptogram = tryConvertNumberKeySet(key) ? VigenereCipher.encrypt(convertToNumericKeySet(key), plainText) : null;
            case VERNAM_CIPHER -> cryptogram = tryConvertNumberKeySet(key) ? VernamCipher.encrypt(convertToNumericKeySet(key), plainText) : null;
            case ONE_TIME_PAD -> cryptogram = OneTimePad.encrypt(plainText);
        }

        return cryptogram;
    }

    private static String decrypt(final Algorithm alg, final String key, final String cipherText) {
        String message = null;

        switch (alg) {
            case CAESAR_CIPHER -> message = tryConvertToNumericKey(key) ? CaesarCipher.decrypt(convertToNumericKey(key), cipherText) : null;
            case VIGENERE_CIPHER -> message = tryConvertNumberKeySet(key) ? VigenereCipher.decrypt(convertToNumericKeySet(key), cipherText) : null;
            case VERNAM_CIPHER -> message = tryConvertNumberKeySet(key) ? VernamCipher.decrypt(convertToNumericKeySet(key), cipherText) : null;
            case ONE_TIME_PAD -> message = OneTimePad.encrypt(cipherText);
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

    private static boolean tryConvertToNumericKey(final String key) {
        try {
            Integer.parseInt(key);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean tryConvertNumberKeySet(final String key) {
        for (int i = 0; i < key.length(); i++){
            tryConvertToNumericKey(String.valueOf(key.charAt(i)));
        }
        return true;
    }

    private static int[] convertToNumericKeySet(final String key) {
        int[] numKeys = new int[key.length()];
        for (int i = 0; i < key.length(); i++){
            numKeys[i] = convertToNumericKey(String.valueOf(key.charAt(i)));
        }

        return numKeys;
    }
}

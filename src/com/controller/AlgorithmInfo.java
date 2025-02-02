package com.controller;

import com.model.enums.Algorithm;
import com.model.enums.Option;

import java.util.HashMap;
import java.util.Map;

public final class AlgorithmInfo {
    public static String[] getAllAlgorithmDescriptions() {
        return new String[] {
                "Takes in a numeric key. Shifts each character in the message by the key.",
                "Takes in a numeric key of any length. Shifts each character in the message by each digit of the key.",
                "Takes in a numeric key of any length. XOR each character in the message by each digit of the key.",
                "Same as Vernam Cipher except the key must be the same length as the message. The key must not be used again for another message."
        };
    }

    public static Map<Algorithm, Option[]> getAlgorithmOptions() {
        Map<Algorithm, Option[]> map = new HashMap<>();

        map.put(Algorithm.CAESAR_CIPHER, new Option[] {Option.ENCRYPT, Option.DECRYPT});
        map.put(Algorithm.VIGENERE_CIPHER, new Option[] {Option.ENCRYPT, Option.DECRYPT});
        map.put(Algorithm.VERNAM_CIPHER, new Option[] {Option.ENCRYPT, Option.DECRYPT});
        map.put(Algorithm.ONE_TIME_PAD, new Option[] {Option.ENCRYPT, Option.DECRYPT, Option.GEN_KEY});

        return map;
    }
}

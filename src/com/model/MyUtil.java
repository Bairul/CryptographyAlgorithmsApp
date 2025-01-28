package com.model;

import com.model.enums.Algorithm;
import com.model.enums.Option;

import java.util.HashMap;
import java.util.Map;

public final class MyUtil {

    private static String formatString(String input) {
        // Convert input to lowercase and split by underscores
        String[] words = input.toLowerCase().split("_");

        // Capitalize the first letter of each word
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }

        return String.join(" ", words);
    }


    private static String[] getAllAlgorithmDescriptions() {
        return new String[] {
                "Takes in a numeric key. Shifts each character in the message by the key.",
                "Takes in a numeric key of any length. Shifts each character in the message by each digit of the key.",
                "This is vernam cipher",
                "This is one time pad"
        };
    }

    public static AlgorithmItem[] getAllAlgorithmNames() {
        AlgorithmItem[] algorithmItems = new AlgorithmItem[Algorithm.values().length];
        String[] algorithmDesc = getAllAlgorithmDescriptions();
        int i = 0;
        for (Algorithm a : Algorithm.values()) {
            algorithmItems[i] = new AlgorithmItem(a, formatString(a.name()), algorithmDesc[i]);
            i++;
        }

        return algorithmItems;
    }

    public static Map<Algorithm, Option[]> getAlgorithmOptions() {
        Map<Algorithm, Option[]> map = new HashMap<>();

        // Caesar
        map.put(Algorithm.CAESAR_CIPHER, new Option[] {Option.ENCRYPT, Option.DECRYPT});
        map.put(Algorithm.VIGENERE_CIPHER, new Option[] {Option.ENCRYPT, Option.DECRYPT});
        map.put(Algorithm.VERNAM_CIPHER, new Option[] {Option.ENCRYPT_EC, Option.DECRYPT_EC, Option.SIGN, Option.VERIFY});
        map.put(Algorithm.ONE_TIME_PAD, new Option[] {Option.MAC, Option.GEN_KEYPAIR});

        return map;
    }
}

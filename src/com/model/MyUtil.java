package com.model;

import com.controller.AlgorithmInfo;
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

    public static AlgorithmItem[] getAllAlgorithmNames() {
        AlgorithmItem[] algorithmItems = new AlgorithmItem[Algorithm.values().length];
        String[] algorithmDesc = AlgorithmInfo.getAllAlgorithmDescriptions();
        int i = 0;
        for (Algorithm a : Algorithm.values()) {
            algorithmItems[i] = new AlgorithmItem(a, formatString(a.name()), algorithmDesc[i]);
            i++;
        }

        return algorithmItems;
    }
}

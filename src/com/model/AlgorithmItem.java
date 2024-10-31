package com.model;

public class AlgorithmItem {
    private final String name;
    private final String description;
    private final Algorithm algorithm;

    public AlgorithmItem(Algorithm alg, String name, String description) {
        this.name = name;
        this.description = description;
        this.algorithm = alg;
    }

    public String getDescription() {
        return description;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {
        return name;
    }
}

package com.i4digital.i4digitalTest.costants;

public enum TypeRisk {
    HIGH("Alto"),
    MEDIUM("Medio"),
    Low("Bajo");

    public final String label;

    TypeRisk(String label) {
        this.label = label;
    }
}

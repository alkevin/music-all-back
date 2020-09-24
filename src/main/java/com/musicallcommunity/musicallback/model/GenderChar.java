package com.musicallcommunity.musicallback.model;

public enum GenderChar {
    M("Homme"),
    F("Femme");

    private String label;

    GenderChar(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

package com.ekwateur.model;

public enum Title {

    M("Monsieur"), MME("Madame"), OTHER("Autre");

    public final String label;

    private Title(String label) {
        this.label = label;
    }
}

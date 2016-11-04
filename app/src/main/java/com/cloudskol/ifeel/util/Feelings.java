package com.cloudskol.ifeel.util;

/**
 * @author tham
 */

public enum Feelings {
    HAPPY("Happy"),
    RELAXED("Relaxed"),
    SAD("Sad"),
    ANGRY("Angry");

    private String name;
    Feelings(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

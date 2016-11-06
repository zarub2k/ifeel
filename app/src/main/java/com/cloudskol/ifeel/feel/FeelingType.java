package com.cloudskol.ifeel.feel;

/**
 * @author tham
 */

public enum FeelingType {
    ANGRY("Angry"),
    HAPPY("Happy"),
    RELAXED("Relaxed"),
    SAD("Sad");

    private String name;
    FeelingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

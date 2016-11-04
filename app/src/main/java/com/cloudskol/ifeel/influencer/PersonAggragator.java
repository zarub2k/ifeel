package com.cloudskol.ifeel.influencer;

/**
 * @author tham
 */

public class PersonAggragator {
    private String name;
    private int count;

    public PersonAggragator(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}

package com.cloudskol.ifeel.influencer;

/**
 * @author tham
 */

public class InfluencerAggregation {
    private String person;
    private String feeling;
    private int count;

    public InfluencerAggregation(String person, String feeling, int count) {
        this.person = person;
        this.feeling = feeling;
        this.count = count;
    }

    public String getPerson() {
        return person;
    }

    public String getFeeling() {
        return feeling;
    }

    public int getCount() {
        return count;
    }
}

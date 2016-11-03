package com.cloudskol.ifeel.trend;

/**
 * @author tham
 */

public class TrendAggregationByFeeling {
    private String name;
    private int count;

    public TrendAggregationByFeeling(String name, int count) {
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

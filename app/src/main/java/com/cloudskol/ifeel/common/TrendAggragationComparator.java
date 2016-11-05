package com.cloudskol.ifeel.common;

import com.cloudskol.ifeel.trend.TrendAggregationByFeeling;

import java.util.Comparator;

/**
 * @author tham
 */

public class TrendAggragationComparator implements Comparator<TrendAggregationByFeeling> {

    @Override
    public int compare(TrendAggregationByFeeling agg1,
                       TrendAggregationByFeeling agg2) {
        return agg1.getName().compareTo(agg2.getName());
    }
}

package com.cloudskol.ifeel.common;

import com.cloudskol.ifeel.influencer.InfluencerAggregation;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by tham on 05-11-2016.
 */

public class InfluencerComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> e1,
                       Map.Entry<String, Integer> e2) {
        return e1.getValue().compareTo(e2.getValue());
    }
}

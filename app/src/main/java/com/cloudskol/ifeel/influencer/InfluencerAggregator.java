package com.cloudskol.ifeel.influencer;

import com.cloudskol.ifeel.util.Feelings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tham
 */

public class InfluencerAggregator {
    private static final String LOG_TAG = InfluencerAggregator.class.getSimpleName();

    private static final InfluencerAggregator instance = new InfluencerAggregator();
    private InfluencerAggregator() {}

    public static final synchronized InfluencerAggregator getInstance() {
        return instance;
    }

    public List<InfluencerAggregation> getPositiveInfluencers(List<InfluencerAggregation> aggregations) {
        if (aggregations == null || aggregations.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<InfluencerAggregation> filteredAggregations = new ArrayList<>(8);

        for (InfluencerAggregation aggregation : aggregations) {
            if (Feelings.HAPPY.getName().equals(aggregation.getFeeling())
                || Feelings.RELAXED.getName().equals(aggregation.getFeeling())) {
                filteredAggregations.add(aggregation);
            }
        }

        return filteredAggregations;
    }

    public List<InfluencerAggregation> getNegativeInfluencers(List<InfluencerAggregation> aggregations) {
        if (aggregations == null || aggregations.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<InfluencerAggregation> filteredAggregations = new ArrayList<>(8);

        for (InfluencerAggregation aggregation : aggregations) {
            if (Feelings.SAD.getName().equals(aggregation.getFeeling())
                    || Feelings.ANGRY.getName().equals(aggregation.getFeeling())) {
                filteredAggregations.add(aggregation);
            }
        }

        return filteredAggregations;
    }

    public Map<String, Integer> getPositiveByPerson(List<InfluencerAggregation> aggregations) {
        if (aggregations == null || aggregations.isEmpty()) {
            return Collections.EMPTY_MAP;
        }

        Map<String, Integer> byPersonMap = new HashMap<>(8);

        for (InfluencerAggregation aggregation : aggregations) {
            if (Feelings.HAPPY.getName().equals(aggregation.getFeeling())
                    || Feelings.RELAXED.getName().equals(aggregation.getFeeling())) {
                final Integer total = byPersonMap.get(aggregation.getPerson());
                if (total == null) {
                    byPersonMap.put(aggregation.getPerson(), aggregation.getCount());
                } else {
                    byPersonMap.put(aggregation.getPerson(), total + aggregation.getCount());
                }
            }
        }

        return byPersonMap;
    }

    public Map<String, Integer> getNegativeByPerson(List<InfluencerAggregation> aggregations) {
        if (aggregations == null || aggregations.isEmpty()) {
            return Collections.EMPTY_MAP;
        }

        Map<String, Integer> byPersonMap = new HashMap<>(8);

        for (InfluencerAggregation aggregation : aggregations) {
            if (Feelings.SAD.getName().equals(aggregation.getFeeling())
                    || Feelings.ANGRY.getName().equals(aggregation.getFeeling())) {
                final Integer total = byPersonMap.get(aggregation.getPerson());
                if (total == null) {
                    byPersonMap.put(aggregation.getPerson(), aggregation.getCount());
                } else {
                    byPersonMap.put(aggregation.getPerson(), total + aggregation.getCount());
                }
            }
        }

        return byPersonMap;
    }
}

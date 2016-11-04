package com.cloudskol.ifeel.influencer;

import com.cloudskol.ifeel.util.Feelings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}

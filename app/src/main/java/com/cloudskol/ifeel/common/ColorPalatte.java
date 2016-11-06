package com.cloudskol.ifeel.common;

import android.graphics.Color;

import com.cloudskol.ifeel.feel.FeelingType;
import com.cloudskol.ifeel.trend.TrendAggregationByFeeling;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */

public class ColorPalatte extends ColorTemplate {
    private static final int ANGRY = Color.rgb(244, 67, 54);
    private static final int HAPPY = Color.rgb(76, 175, 80);
    private static final int RELAXED = Color.rgb(96, 125, 139);
    private static final int SAD = Color.rgb(255, 152, 0);

    public static final int[] FEELING_COLORS = {
            Color.rgb(244, 67, 54), //Angry
            Color.rgb(76, 175, 80), //Happy
            Color.rgb(96, 125, 139), //Relaxed
            Color.rgb(255, 152, 0) //Sad
    };

    public static final int[] POSITIVE_COLORS = {
            Color.rgb(76, 175, 80),
            Color.rgb(139, 195, 74),
            Color.rgb(205, 220, 57),
            Color.rgb(255, 193, 7),
            Color.rgb(255, 235, 59)

    };

    public static final int[] NEGATIVE_COLORS = {
            Color.rgb(76, 175, 80),
            Color.rgb(139, 195, 74),
            Color.rgb(205, 220, 57),
            Color.rgb(255, 193, 7),
            Color.rgb(255, 235, 59)

    };

    public static final List<Integer> getFeelingColors() {
        List<Integer> colors = new ArrayList<Integer>(4);
        for (int code : FEELING_COLORS) {
            colors.add(code);
        }

        return colors;
    }

    public static final List<Integer> getFeelingColors(List<TrendAggregationByFeeling> aggregations) {
        if (aggregations == null || aggregations.isEmpty()) {
            return null;
        }

        List<Integer> colors = new ArrayList<Integer>(aggregations.size());
        for (TrendAggregationByFeeling aggregation : aggregations) {
            colors.add(getFeelingColor(aggregation));
        }

        return colors;
    }

    private static int getFeelingColor(TrendAggregationByFeeling aggregation) {
        int colorCode = 0;

        if (aggregation.getName().equals(FeelingType.ANGRY.getName())) {
            colorCode = ANGRY;
        } else if (aggregation.getName().equals(FeelingType.HAPPY.getName())) {
            colorCode = HAPPY;
        } else if (aggregation.getName().equals(FeelingType.RELAXED.getName())) {
            colorCode = RELAXED;
        } else if (aggregation.getName().equals(FeelingType.SAD.getName())) {
            colorCode = SAD;
        }

        return colorCode;
    }

    public static final List<Integer> getPositiveColors() {
        List<Integer> colors = new ArrayList<Integer>(4);
        for (int code : POSITIVE_COLORS) {
            colors.add(code);
        }

        return colors;
    }

    public static final List<Integer> getNegativeColors() {
        List<Integer> colors = new ArrayList<Integer>(4);
        for (int code : NEGATIVE_COLORS) {
            colors.add(code);
        }

        return colors;
    }
}

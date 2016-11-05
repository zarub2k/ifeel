package com.cloudskol.ifeel.common;

import android.graphics.Color;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */

public class ColorPalatte extends ColorTemplate {
    public static final int[] FEELING_COLORS = {
            Color.rgb(244, 67, 54), //Angry
            Color.rgb(76, 175, 80), //Happy
            Color.rgb(96, 125, 139),
            Color.rgb(255, 152, 0)
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

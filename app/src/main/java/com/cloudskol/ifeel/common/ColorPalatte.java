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

    public static final List<Integer> getFeelingColors() {
        List<Integer> colors = new ArrayList<Integer>(4);
        for (int code : FEELING_COLORS) {
            colors.add(code);
        }

        return colors;
    }
}

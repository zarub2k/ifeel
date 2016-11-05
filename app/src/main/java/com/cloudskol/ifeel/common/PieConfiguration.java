package com.cloudskol.ifeel.common;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;

/**
 * Created by tham on 05-11-2016.
 */

public class PieConfiguration {
    private static final PieConfiguration instance = new PieConfiguration();
    private PieConfiguration() {}

    public static final synchronized PieConfiguration getInstance() {
        return instance;
    }

    public void setDefaultConfiguration(PieChart pieChart) {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDrawCenterText(true);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.highlightValues(null);
        pieChart.setNoDataText("No data available");

        pieChart.setEntryLabelTextSize(25f);

        Legend legend = pieChart.getLegend();
        legend.setTextSize(15f);
    }
}

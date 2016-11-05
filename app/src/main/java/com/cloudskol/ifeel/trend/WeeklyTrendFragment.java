package com.cloudskol.ifeel.trend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudskol.ifeel.R;
import com.cloudskol.ifeel.common.PieConfiguration;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class WeeklyTrendFragment extends Fragment {
    public WeeklyTrendFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weekly_trend, container, false);
        renderPieChart(view);

        return view;
    }

    private void renderPieChart(View view) {
        PieChart weeklyTrend = (PieChart) view.findViewById(R.id.weeklyTrend);
        PieConfiguration.getInstance().setDefaultConfiguration(weeklyTrend);

        weeklyTrend.setData(getChartData());
        weeklyTrend.notifyDataSetChanged();
        weeklyTrend.invalidate();
    }

    private PieData getChartData() {
        final List<TrendAggregationByFeeling> weeklyTrendAggregations = TrendQueryManager.getInstance(
                this.getContext()).weeklyTrend();
        List<PieEntry> entries = new ArrayList<PieEntry>(weeklyTrendAggregations.size());
        for (TrendAggregationByFeeling trendAggregation : weeklyTrendAggregations) {
            entries.add(new PieEntry(trendAggregation.getCount(), trendAggregation.getName()));
        }

        final PieDataSet pieDataSet = new PieDataSet(entries, null);

        List<Integer> colors = new ArrayList<>(8);
        for (int color : ColorTemplate.JOYFUL_COLORS) {
            colors.add(color);
        }

        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(3f);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(20f);

        return pieData;
    }
}

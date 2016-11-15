package com.cloudskol.ifeel.trend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudskol.ifeel.R;
import com.cloudskol.ifeel.common.ColorPalatte;
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
    private static final String LOG_TAG = WeeklyTrendFragment.class.getSimpleName();

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

        PieData chartData = getChartData();
        if (chartData == null) {
            Log.v(LOG_TAG, "Weekly chart data is empty");
            return;
        }

        weeklyTrend.setData(chartData);
        weeklyTrend.invalidate();
    }

    private PieData getChartData() {
        final List<TrendAggregationByFeeling> weeklyTrendAggregations = TrendQueryManager.getInstance()
                .weeklyTrend(getContext().getContentResolver());
        List<PieEntry> entries = new ArrayList<PieEntry>(weeklyTrendAggregations.size());
        for (TrendAggregationByFeeling trendAggregation : weeklyTrendAggregations) {
            entries.add(new PieEntry(trendAggregation.getCount(), trendAggregation.getName()));
        }

        if (entries.size() == 0) {
            return null;
        }

        final PieDataSet pieDataSet = new PieDataSet(entries, null);
        pieDataSet.setColors(ColorPalatte.getFeelingColors(weeklyTrendAggregations));
        pieDataSet.setSliceSpace(3f);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(20f);

        return pieData;
    }
}

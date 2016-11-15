package com.cloudskol.ifeel.trend;

import android.graphics.Color;
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
public class TodaysTrendFragment extends Fragment {
    private static final String LOG_TAG = TodaysTrendFragment.class.getSimpleName();

    public TodaysTrendFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_todays_trend, container, false);
        renderPieChart(view);

        return view;
    }

    private void renderPieChart(View view) {
        PieChart todayTrend = (PieChart) view.findViewById(R.id.todayTrend);
        PieConfiguration.getInstance().setDefaultConfiguration(todayTrend);

        PieData chartData = getChartData();
        if (chartData == null) {
            return;
        }

        todayTrend.setData(chartData);
        todayTrend.invalidate();
    }

    private PieData getChartData() {
        final List<TrendAggregationByFeeling> todaysTrendAggregations = TrendQueryManager.getInstance()
                .todaysTrend(getContext().getContentResolver());
        List<PieEntry> entries = new ArrayList<PieEntry>(todaysTrendAggregations.size());
        for (TrendAggregationByFeeling trendAggregation : todaysTrendAggregations) {
            Log.v(LOG_TAG, trendAggregation.getName() + " > " + trendAggregation.getCount());
            entries.add(new PieEntry(trendAggregation.getCount(), trendAggregation.getName()));
        }

        if (entries.size() == 0) {
            return null;
        }

        final PieDataSet pieDataSet = new PieDataSet(entries, null);
        pieDataSet.setColors(ColorPalatte.getFeelingColors(todaysTrendAggregations));
        pieDataSet.setSliceSpace(2f);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(25f);
        pieData.setValueTextColor(Color.WHITE);

        return pieData;
    }
}

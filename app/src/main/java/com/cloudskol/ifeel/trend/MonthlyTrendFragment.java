package com.cloudskol.ifeel.trend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class MonthlyTrendFragment extends Fragment {
    public MonthlyTrendFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_monthly_trend, container, false);
        renderPieChart(view);

        return view;
    }

    private void renderPieChart(View view) {
        PieChart monthlyTrend = (PieChart) view.findViewById(R.id.monthlyTrend);
        PieConfiguration.getInstance().setDefaultConfiguration(monthlyTrend);

        monthlyTrend.setData(getChartData());
        monthlyTrend.invalidate();
    }

    private PieData getChartData() {
        final List<TrendAggregationByFeeling> monthlyTrendAggregations = TrendQueryManager.getInstance(
                this.getContext()).monthlyTrend();
        List<PieEntry> entries = new ArrayList<PieEntry>(monthlyTrendAggregations.size());
        for (TrendAggregationByFeeling trendAggregation : monthlyTrendAggregations) {
            entries.add(new PieEntry(trendAggregation.getCount(), trendAggregation.getName()));
        }

        final PieDataSet pieDataSet = new PieDataSet(entries, null);
        pieDataSet.setColors(ColorPalatte.getFeelingColors(monthlyTrendAggregations));
        pieDataSet.setSliceSpace(3f);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(20f);

        return pieData;
    }
}

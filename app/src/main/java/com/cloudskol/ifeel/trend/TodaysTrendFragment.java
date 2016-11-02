package com.cloudskol.ifeel.trend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudskol.ifeel.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class TodaysTrendFragment extends Fragment {
    public TodaysTrendFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_todays_trend, container, false);
        renderPieChart(view);

        return view;
    }

    private void renderPieChart(View view) {
        final List<TodaysTrendAggregation> todaysTrendAggregations = TrendQueryManager.getInstance(this.getContext()).todaysTrend();

        PieChart todayTrend = (PieChart) view.findViewById(R.id.todayTrend);
        todayTrend.setUsePercentValues(true);
        todayTrend.getDescription().setEnabled(false);


        List<PieEntry> entries = new ArrayList<PieEntry>(todaysTrendAggregations.size());
        for (TodaysTrendAggregation trendAggregation : todaysTrendAggregations) {
            entries.add(new PieEntry(trendAggregation.getCount(), trendAggregation.getName()));
        }


        final PieDataSet pieDataSet = new PieDataSet(entries, null);

        List<Integer> colors = new ArrayList<>(8);
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        pieDataSet.setColors(colors);

        todayTrend.setData(new PieData(pieDataSet));
        todayTrend.invalidate();
    }
}

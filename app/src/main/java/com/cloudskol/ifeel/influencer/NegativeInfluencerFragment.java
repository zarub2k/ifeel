package com.cloudskol.ifeel.influencer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudskol.ifeel.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class NegativeInfluencerFragment extends Fragment {

    public NegativeInfluencerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_negative_influencer, container, false);

        renderPieChart(view);

        return view;
    }

    private void renderPieChart(View view) {
        PieChart monthlyTrend = (PieChart) view.findViewById(R.id.negativeInfluencer);
        setChartConfiguration(monthlyTrend);

        monthlyTrend.setData(getChartData());
        monthlyTrend.notifyDataSetChanged();
        monthlyTrend.invalidate();
    }

    private PieData getChartData() {
        final List<InfluencerAggregation> influencers = InfluencerQueryManager.getInstance(
                this.getContext()).getInfluencers();

        final Map<String, Integer> positiveByPerson = InfluencerAggregator.getInstance().getNegativeByPerson(influencers);


        List<PieEntry> entries = new ArrayList<PieEntry>(positiveByPerson.size());
        final Set<Map.Entry<String, Integer>> personEntrySet = positiveByPerson.entrySet();
        for (Map.Entry<String, Integer> entry : personEntrySet) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
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

    private void setChartConfiguration(PieChart pieChart) {
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

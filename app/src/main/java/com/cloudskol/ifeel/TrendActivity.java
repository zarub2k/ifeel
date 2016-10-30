package com.cloudskol.ifeel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        renderUi();
    }

    private void renderUi() {
        PieChart todayTrend = (PieChart) findViewById(R.id.todayTrend);
        todayTrend.setUsePercentValues(true);


        List<PieEntry> entries = new ArrayList<PieEntry>(8);

        entries.add(new PieEntry(10, "Apple"));
        entries.add(new PieEntry(12, "Orange"));
        entries.add(new PieEntry(8, "Grapes"));
        entries.add(new PieEntry(20, "Banana"));

        final PieDataSet pieDataSet = new PieDataSet(entries, "Today");

        List<Integer> colors = new ArrayList<Integer>(8);
        for (int color : ColorTemplate.COLORFUL_COLORS) {
            colors.add(color);
        }

        pieDataSet.setColors(colors);

        todayTrend.setData(new PieData(pieDataSet));
    }
}

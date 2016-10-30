package com.cloudskol.ifeel.trend;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cloudskol.ifeel.R;

public class FeelingsTrendActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_trend);

        renderUi();
    }

    private void renderUi() {
        final TrendPagerAdapter trendPagerAdapter = new TrendPagerAdapter(getSupportFragmentManager());

        ViewPager trendPager = (ViewPager) findViewById(R.id.activity_feelings_trend);
        trendPager.setAdapter(trendPagerAdapter);
    }
}

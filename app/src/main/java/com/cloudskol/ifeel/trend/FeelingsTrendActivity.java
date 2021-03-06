package com.cloudskol.ifeel.trend;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cloudskol.ifeel.feel.CreateFeelActivity;
import com.cloudskol.ifeel.R;

public class FeelingsTrendActivity extends AppCompatActivity {
    private static final String LOG_TAG = FeelingsTrendActivity.class.getSimpleName();

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager trendPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_trend);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        trendPager = (ViewPager) findViewById(R.id.trendPager);
        setupViewPager(trendPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(trendPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                final Intent intent = new Intent(FeelingsTrendActivity.this, CreateFeelActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        TrendPagerAdapter adapter = new TrendPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}

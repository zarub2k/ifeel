package com.cloudskol.ifeel.trend;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cloudskol.ifeel.R;

public class FeelingsTrendActivity extends AppCompatActivity {
    private static final String LOG_TAG = FeelingsTrendActivity.class.getSimpleName();

    private static final String[] trendTabs = {"Today", "Weekly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_trend);

        renderUi();
    }

    private void renderUi() {
        final TrendPagerAdapter trendPagerAdapter = new TrendPagerAdapter(getSupportFragmentManager());

        final ViewPager trendPager = (ViewPager) findViewById(R.id.activity_feelings_trend);
        trendPager.setAdapter(trendPagerAdapter);
        trendPager.setOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getActionBar().setSelectedNavigationItem(position);
            }
        });

        final ActionBar actionBar = getActionBar();
        Log.v(LOG_TAG, "Action bar: " + actionBar);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                trendPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

            }
        };



        for (int i = 0; i < trendTabs.length; i++) {
            actionBar.addTab(actionBar.newTab()
                    .setText(trendTabs[i])
                    .setTabListener(tabListener));
        }
    }
}

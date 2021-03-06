package com.cloudskol.ifeel.trend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author tham
 */

public class TrendPagerAdapter extends FragmentPagerAdapter {
    private static final String[] trendTabs = {"Today", "Weekly", "Monthly"};

    public TrendPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TodaysTrendFragment();
                break;
            case 1:
                fragment = new WeeklyTrendFragment();
                break;

            case 2:
                fragment = new MonthlyTrendFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return trendTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return trendTabs[position];
    }
}

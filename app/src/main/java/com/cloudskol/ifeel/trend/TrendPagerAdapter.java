package com.cloudskol.ifeel.trend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author tham
 */

public class TrendPagerAdapter extends FragmentPagerAdapter {
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
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

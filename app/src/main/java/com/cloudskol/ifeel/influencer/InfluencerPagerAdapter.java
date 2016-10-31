package com.cloudskol.ifeel.influencer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tham on 31-10-2016.
 */

public class InfluencerPagerAdapter extends FragmentPagerAdapter {
    private static final String[] titles = {"Positive", "Negative"};

    public InfluencerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PositiveInfluencerFragment();
                break;

            case 1:
                fragment = new NegativeInfluencerFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

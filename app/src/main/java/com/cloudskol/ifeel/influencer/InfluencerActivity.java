package com.cloudskol.ifeel.influencer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cloudskol.ifeel.R;
import com.cloudskol.ifeel.influencer.InfluencerPagerAdapter;
import com.cloudskol.ifeel.influencer.NegativeInfluencerFragment;
import com.cloudskol.ifeel.influencer.PositiveInfluencerFragment;

public class InfluencerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager influencerPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_influencer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        influencerPager = (ViewPager) findViewById(R.id.influencerPager);
        setupViewPager(influencerPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(influencerPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        InfluencerPagerAdapter adapter = new InfluencerPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}

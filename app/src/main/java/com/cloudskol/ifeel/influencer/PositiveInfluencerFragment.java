package com.cloudskol.ifeel.influencer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudskol.ifeel.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PositiveInfluencerFragment extends Fragment {

    public PositiveInfluencerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_positive_influencer, container, false);

        final List<InfluencerAggregation> influencers = InfluencerQueryManager.getInstance(
                this.getContext()).getInfluencers();

        return view;
    }

}

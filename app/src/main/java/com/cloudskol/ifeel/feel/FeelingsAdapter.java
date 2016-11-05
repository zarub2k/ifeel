package com.cloudskol.ifeel.feel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloudskol.ifeel.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */

public class FeelingsAdapter extends BaseAdapter {
    private static final String LOG_TAG = FeelingsAdapter.class.getSimpleName();

    private LayoutInflater inflater;
    private List<Feeling> feelings = new ArrayList<>(2);

    public FeelingsAdapter(FeelListActivity context, List<Feeling> feelings) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initializeFeelings(feelings);
    }

    @Override
    public int getCount() {
        return feelings.size();
    }

    @Override
    public Object getItem(int position) {
        return feelings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeelingViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.feelings_list_item, null, false);

            viewHolder = new FeelingViewHolder();
            viewHolder.person = (TextView) convertView.findViewById(R.id.person);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FeelingViewHolder) convertView.getTag();
        }

        final Feeling feeling = feelings.get(position);
        viewHolder.person.setText(feeling.getPerson());

        return convertView;
    }

    private class FeelingViewHolder {
        TextView date;
        TextView feeling;
        TextView person;
        TextView summary;
    }

    private void initializeFeelings(List<Feeling> feelings) {
        if (isFeelingsEmpty(feelings)) {
            return;
        }

        this.feelings.clear();
        this.feelings.addAll(feelings);
    }

    private boolean isFeelingsEmpty(List<Feeling> feelings) {
        return feelings == null || feelings.isEmpty();
    }
}

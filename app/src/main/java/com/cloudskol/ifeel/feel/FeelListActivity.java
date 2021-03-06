package com.cloudskol.ifeel.feel;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudskol.ifeel.R;

import java.util.List;

public class FeelListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feel_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateCreate();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        renderUi();
    }

    private void renderUi() {
        List<Feeling> feelings = FeelingsQueryManager.getInstance(this).feelings(getContentResolver());

        ListView feelingsListView = (ListView) findViewById(R.id.feelings_list);

        TextView emptyTextView = (TextView) findViewById(R.id.msg_empty);
        feelingsListView.setEmptyView(emptyTextView);

        final FeelingsAdapter feelingsAdapter = new FeelingsAdapter(this, feelings);
        feelingsListView.setAdapter(feelingsAdapter);

        feelingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Feeling feeling = (Feeling) parent.getItemAtPosition(position);
                navigateUpdate(feeling);
            }
        });
    }

    private void navigateUpdate(Feeling feeling) {
        final Intent intent = new Intent(this, UpdateFeelActivity.class);
        intent.putExtra("SelectedFeel", feeling);
        startActivity(intent);
    }

    private void navigateCreate() {
        Intent intent = new Intent(this, CreateFeelActivity.class);
        startActivity(intent);
    }
}

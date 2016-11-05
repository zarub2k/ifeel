package com.cloudskol.ifeel.feel;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
                startIntent();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        renderUi();
    }

    private void renderUi() {
        List<Feeling> feelings = FeelingsQueryManager.getInstance(this).feelings();
    }

    private void startIntent() {
        Intent intent = new Intent(this, FeelActivity.class);
        startActivity(intent);
    }
}

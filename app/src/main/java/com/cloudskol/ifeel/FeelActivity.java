package com.cloudskol.ifeel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FeelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        renderUi();
    }

    private void renderUi() {
        final RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                ToggleButton toggleButton;
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    toggleButton = (ToggleButton) radioGroup.getChildAt(i);
                    toggleButton.setChecked(checkedId == toggleButton.getId());
                }
            }
        };

        RadioGroup feelingsRadioGroup = (RadioGroup) findViewById(R.id.feelings_group);
        feelingsRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public void onToggle(View view) {
        ((RadioGroup)view.getParent()).check(view.getId());

        Toast.makeText(this, "Selected Toggle " + ((ToggleButton)view).getText(), Toast.LENGTH_SHORT).show();
    }
}

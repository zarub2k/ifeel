package com.cloudskol.ifeel.feel;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.cloudskol.ifeel.R;
import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.util.DateUtility;

public class UpdateFeelActivity extends AppCompatActivity {
    private static final String LOG_TAG = UpdateFeelActivity.class.getSimpleName();

    private String selectedFeeling;
    private Feeling currentFeel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feel);
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
        currentFeel = (Feeling) getIntent().getSerializableExtra("SelectedFeel");

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

        setCurrentValue(currentFeel);
    }

    private void setCurrentValue(Feeling feeling) {
        selectedFeeling = feeling.getFeeling();

        checkToggleButton(feeling);

        EditText personText = (EditText) findViewById(R.id.txt_person);
        personText.setText(feeling.getPerson());

        EditText summaryText = (EditText) findViewById(R.id.txt_summary);
        summaryText.setText(feeling.getSummary());
    }

    private void checkToggleButton(Feeling feeling) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.feelings_group);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            final ToggleButton toggleButton = (ToggleButton) radioGroup.getChildAt(i);
            toggleButton.setChecked(toggleButton.getText().equals(feeling.getFeeling()));
        }
    }

    public void onSave(View view) {
//        Toast.makeText(this, "Save clicked", Toast.LENGTH_SHORT).show();
        FeelingsQueryManager.getInstance(this).updateFeeling(currentFeel.getId(), getContentValues());

        final Intent intent = new Intent(this, FeelListActivity.class);
        startActivity(intent);
    }

    public void onToggle(View view) {
        ((RadioGroup)view.getParent()).check(view.getId());
        selectedFeeling = String.valueOf(((ToggleButton)view).getText());

//        Toast.makeText(this, "Selected Toggle " + ((ToggleButton)view).getText(), Toast.LENGTH_SHORT).show();
    }

    private ContentValues getContentValues() {
        final ContentValues contentValues = new ContentValues();

        contentValues.put(FeelContract.FeelEntry.COLUMN_FEELING, selectedFeeling);

        EditText personText = (EditText) findViewById(R.id.txt_person);
        contentValues.put(FeelContract.FeelEntry.COLUMN_PERSON, personText.getText().toString());

        EditText summaryText = (EditText) findViewById(R.id.txt_summary);
        contentValues.put(FeelContract.FeelEntry.COLUMN_SUMMARY, summaryText.getText().toString());

        contentValues.put(FeelContract.FeelEntry.COLUMN_DATE, DateUtility.getInstance().getFormattedToday());

        Log.v(LOG_TAG, "Content values: " + contentValues);

        return contentValues;
    }

    private void navigateCreate() {
        Intent intent = new Intent(this, CreateFeelActivity.class);
        startActivity(intent);
    }
}

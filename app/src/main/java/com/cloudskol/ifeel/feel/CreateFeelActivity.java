package com.cloudskol.ifeel.feel;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cloudskol.ifeel.R;
import com.cloudskol.ifeel.db.FeelContentProvider;
import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.util.DateUtility;

public class CreateFeelActivity extends AppCompatActivity {
    private static final String LOG_TAG = CreateFeelActivity.class.getSimpleName();

    private String selectedFeeling = null;

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
        selectedFeeling = String.valueOf(((ToggleButton)view).getText());

//        Toast.makeText(this, "Selected Toggle " + ((ToggleButton)view).getText(), Toast.LENGTH_SHORT).show();
    }

    public void onSave(View view) {
        if (!isValidFeeling()) {
            Toast.makeText(this, "Select your current feeling", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPerson()) {
            Toast.makeText(this, "Select the person associated with your feeling", Toast.LENGTH_SHORT).show();
            return;
        }

        getContentResolver().insert(FeelContentProvider.CONTENT_URI, getContentValues());

//        FeelingsQueryManager.getInstance(this).createFeeling(getContentValues());

        Toast.makeText(this, "Your current feeling is stored successfully!", Toast.LENGTH_SHORT).show();

        final Intent intent = new Intent(this, FeelListActivity.class);
        startActivity(intent);
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

    private boolean isValidFeeling() {
        if (selectedFeeling == null || selectedFeeling.isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean isValidPerson() {
        EditText personText = (EditText) findViewById(R.id.txt_person);
        Editable personValue = personText.getText();
        if (personValue == null || personValue.toString().isEmpty()) {
            return false;
        }

        return true;
    }
}

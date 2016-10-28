package com.cloudskol.ifeel;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.db.FeelDbHelper;

public class FeelActivity extends AppCompatActivity {
    private static final String LOG_TAG = FeelActivity.class.getSimpleName();

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

        Toast.makeText(this, "Selected Toggle " + ((ToggleButton)view).getText(), Toast.LENGTH_SHORT).show();
    }

    public void onSave(View view) {
        saveFeeling();
        Toast.makeText(this, "Save button clicked", Toast.LENGTH_SHORT).show();
    }

    public void onCancel(View view) {
        Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show();
    }

    private void saveFeeling() {
        final FeelDbHelper feelDbHelper = new FeelDbHelper(this);
        final SQLiteDatabase db = feelDbHelper.getWritableDatabase();

        getContentValues();

    }

    private ContentValues getContentValues() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(FeelContract.FeelEntry.COLUMN_FEELING, selectedFeeling);

        Log.v(LOG_TAG, "Content values: " + contentValues);

        return contentValues;
    }
}

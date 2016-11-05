package com.cloudskol.ifeel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.db.FeelDbHelper;
import com.cloudskol.ifeel.util.DateUtility;

public class FeelActivity extends AppCompatActivity {
    private static final String LOG_TAG = FeelActivity.class.getSimpleName();

    private String selectedFeeling = null;
    final FeelDbHelper feelDbHelper = new FeelDbHelper(this);

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
        Toast.makeText(this, "Your current feeling is stored successfully!", Toast.LENGTH_SHORT).show();
    }

    public void onCancel(View view) {
        final SQLiteDatabase db = feelDbHelper.getReadableDatabase();

        String[] projection = {
                FeelContract.FeelEntry._ID,
                FeelContract.FeelEntry.COLUMN_FEELING,
                FeelContract.FeelEntry.COLUMN_PERSON
        };

        final Cursor cursor = db.query(FeelContract.FeelEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        Log.v(LOG_TAG, "Number of rows: " + cursor.getCount());

        final String storedFeeling = cursor.getString(cursor.getColumnIndexOrThrow(FeelContract.FeelEntry.COLUMN_FEELING));

        Toast.makeText(this, "Cancel button clicked " + storedFeeling, Toast.LENGTH_SHORT).show();
    }

    private void saveFeeling() {
        final SQLiteDatabase db = feelDbHelper.getWritableDatabase();
        db.insert(FeelContract.FeelEntry.TABLE_NAME, null, getContentValues());
        db.close();
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
}

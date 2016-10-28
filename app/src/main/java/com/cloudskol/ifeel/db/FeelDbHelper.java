package com.cloudskol.ifeel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author tham
 */

public class FeelDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = FeelDbHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "iFeel.db";

    public FeelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(buildCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(buildDropTableQuery());
        onCreate(db);
    }

    private String buildCreateTableQuery() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS ");
        builder.append(FeelContract.FeelEntry.TABLE_NAME);
        builder.append(" (");
        builder.append(FeelContract.FeelEntry._ID + " INTEGER PRIMARY KEY, ");
        builder.append(FeelContract.FeelEntry.COLUMN_FEELING + " TEXT, ");
        builder.append(FeelContract.FeelEntry.COLUMN_PERSON + " TEXT, ");
        builder.append(FeelContract.FeelEntry.COLUMN_SUMMARY + " REAL, ");
        builder.append(FeelContract.FeelEntry.COLUMN_DATE + " TEXT");
        builder.append(" )");

        Log.v(LOG_TAG, "Create table query: " + builder.toString());
        return builder.toString();
    }

    private String buildDropTableQuery() {
        return "DROP TABLE IF EXISTS " + FeelContract.FeelEntry.TABLE_NAME;
    }
}

package com.cloudskol.ifeel.feel;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cloudskol.ifeel.common.TrendAggragationComparator;
import com.cloudskol.ifeel.db.FeelContentProvider;
import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.db.FeelDbHelper;
import com.cloudskol.ifeel.trend.TrendAggregationByFeeling;
import com.cloudskol.ifeel.util.DateUtility;
import com.cloudskol.ifeel.util.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tham on 05-11-2016.
 */

public class FeelingsQueryManager {
    private static final String LOG_TAG = FeelingsQueryManager.class.getSimpleName();

    private static FeelDbHelper dbHelper;

    private static final FeelingsQueryManager instance = new FeelingsQueryManager();
    private FeelingsQueryManager() {}
    public static final synchronized FeelingsQueryManager getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new FeelDbHelper(context);
        }

        return instance;
    }

    public List<Feeling> feelings(ContentResolver contentResolver) {
        Log.v(LOG_TAG, "Enters feelings()");
        final Range<String> weeklyRange = DateUtility.getInstance().getWeeklyRange();

        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] columns = new String[] {
                "_id",
                FeelContract.FeelEntry.COLUMN_DATE,
                FeelContract.FeelEntry.COLUMN_FEELING,
                FeelContract.FeelEntry.COLUMN_PERSON,
                FeelContract.FeelEntry.COLUMN_SUMMARY
        };

        String selection = "date between date(?) and date(?)";
        String[] arguments = new String[] { weeklyRange.getStart(), weeklyRange.getEnd() };

        String groupBy = null;
        String having = null;
        String orderBy = "date(date) DESC, _id DESC";

        final Cursor cursor = contentResolver.query(FeelContentProvider.CONTENT_URI, columns,
                selection, arguments, orderBy);


//        final Cursor cursor = database.query(FeelContract.FeelEntry.TABLE_NAME,
//                columns,
//                selection,
//                arguments,
//                groupBy,
//                having,
//                orderBy);

        cursor.moveToFirst();

        List<Feeling> feelings = new ArrayList<>(cursor.getCount());
        Feeling feeling;

        boolean hasData = true;
        while (hasData && cursor.getCount() > 0) {
            feeling = new Feeling(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("date")),
                    cursor.getString(cursor.getColumnIndex("feeling"))
            );

            feeling.setPerson(cursor.getString(cursor.getColumnIndex("person")));
            feeling.setSummary(cursor.getString(cursor.getColumnIndex("summary")));

            feelings.add(feeling);
            hasData = cursor.moveToNext();
        }

        closeCursor(cursor);

        Log.v(LOG_TAG, "Number of feelings searched: " + feelings.size());

        return feelings;
    }

    public void createFeeling(ContentValues contentValues) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(FeelContract.FeelEntry.TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateFeeling(int id, ContentValues contentValues) {
        String where = "_id = ?";
        String[] whereArgs = new String[] { String.valueOf(id)};

        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(FeelContract.FeelEntry.TABLE_NAME, contentValues, where, whereArgs);
        db.close();
    }

    private void closeCursor(Cursor cursor) {
        if (cursor.isClosed()) {
            return;
        }

        cursor.close();
    }
}

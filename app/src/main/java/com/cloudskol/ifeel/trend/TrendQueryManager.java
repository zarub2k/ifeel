package com.cloudskol.ifeel.trend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cloudskol.ifeel.common.TrendAggragationComparator;
import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.db.FeelDbHelper;
import com.cloudskol.ifeel.util.DateUtility;
import com.cloudskol.ifeel.util.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tham
 */

public class TrendQueryManager {
    private final String LOG_TAG = TrendQueryManager.class.getSimpleName();

    private static FeelDbHelper dbHelper;

    private static final TrendQueryManager instance = new TrendQueryManager();
    private TrendQueryManager() {}

    public static final synchronized TrendQueryManager getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new FeelDbHelper(context);
        }

        return instance;
    }

    public List<TrendAggregationByFeeling> monthlyTrend() {
        Log.v(LOG_TAG, "Enters monthlyTrend()");
        final Range<String> monthlyRange = DateUtility.getInstance().getMonthlyRange();

        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] columns = new String[] { "distinct feeling", "count(_id)" };

        String selection = "date between date(?) and date(?)";
        String[] arguments = new String[] { monthlyRange.getStart(), monthlyRange.getEnd() };

        String groupBy = "feeling";

        final Cursor cursor = database.query(FeelContract.FeelEntry.TABLE_NAME,
                columns,
                selection,
                arguments,
                groupBy,
                null,
                null);

        cursor.moveToFirst();

        TrendAggregationByFeeling trendAggregation = null;
        List<TrendAggregationByFeeling> trendAggregations = new ArrayList<>(cursor.getCount());
        boolean hasData = true;
        while (hasData && cursor.getCount() > 0) {
            final String feeling = cursor.getString(cursor.getColumnIndex("feeling"));
            final int count = cursor.getInt(cursor.getColumnIndex("count(_id)"));

            trendAggregation = new TrendAggregationByFeeling(feeling, count);
            trendAggregations.add(trendAggregation);
            Log.v(LOG_TAG, "Aggregation: " + feeling + " >> " + count);

            hasData = cursor.moveToNext();
        }

        Log.v(LOG_TAG, "Total size of the trendAggregations: " + trendAggregations.size());

        closeCursor(cursor);

        Collections.sort(trendAggregations, new TrendAggragationComparator());
        return trendAggregations;
    }


    public List<TrendAggregationByFeeling> weeklyTrend() {
        Log.v(LOG_TAG, "Enters weeklyTrend()");
        final Range<String> weeklyRange = DateUtility.getInstance().getWeeklyRange();

        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] columns = new String[] { "distinct feeling", "count(_id)" };

        String selection = "date between date(?) and date(?)";
        String[] arguments = new String[] { weeklyRange.getStart(), weeklyRange.getEnd() };

        String groupBy = "feeling";

        final Cursor cursor = database.query(FeelContract.FeelEntry.TABLE_NAME,
                columns,
                selection,
                arguments,
                groupBy,
                null,
                null);

        cursor.moveToFirst();

        TrendAggregationByFeeling trendAggregation = null;
        List<TrendAggregationByFeeling> trendAggregations = new ArrayList<>(cursor.getCount());
        boolean hasData = true;
        while (hasData && cursor.getCount() > 0) {
            final String feeling = cursor.getString(cursor.getColumnIndex("feeling"));
            final int count = cursor.getInt(cursor.getColumnIndex("count(_id)"));

            trendAggregation = new TrendAggregationByFeeling(feeling, count);
            trendAggregations.add(trendAggregation);
            Log.v(LOG_TAG, "Aggregation: " + feeling + " >> " + count);

            hasData = cursor.moveToNext();
        }

        Log.v(LOG_TAG, "Total size of the trendAggregations: " + trendAggregations.size());

        closeCursor(cursor);

        Collections.sort(trendAggregations, new TrendAggragationComparator());
        return trendAggregations;
    }

    public List<TrendAggregationByFeeling> todaysTrend() {
        Log.v(LOG_TAG, "Enters todaysTrend()");
        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] columns = new String[] { "distinct feeling", "count(_id)" };

        String selection = "date=date(?)";
        String[] arguments = new String[] { DateUtility.getInstance().getFormattedToday() };

        String groupBy = "feeling";

        final Cursor cursor = database.query(FeelContract.FeelEntry.TABLE_NAME,
                columns,
                selection,
                arguments,
                groupBy,
                null,
                null);

        cursor.moveToFirst();

        TrendAggregationByFeeling trendAggregation;
        List<TrendAggregationByFeeling> trendAggregations = new ArrayList<>(cursor.getCount());
        boolean hasData = true;
        while (hasData && cursor.getCount() > 0) {
            final String feeling = cursor.getString(cursor.getColumnIndex("feeling"));
            final int count = cursor.getInt(cursor.getColumnIndex("count(_id)"));

            trendAggregation = new TrendAggregationByFeeling(feeling, count);
            trendAggregations.add(trendAggregation);
            Log.v(LOG_TAG, "Aggregation: " + feeling + " >> " + count);

            hasData = cursor.moveToNext();
        }

        Log.v(LOG_TAG, "Total size of the trendAggregations: " + trendAggregations.size());

        closeCursor(cursor);

        Collections.sort(trendAggregations, new TrendAggragationComparator());
        return trendAggregations;
    }

    private void closeCursor(Cursor cursor) {
        if (!cursor.isClosed()) {
            cursor.close();
        }
    }
}

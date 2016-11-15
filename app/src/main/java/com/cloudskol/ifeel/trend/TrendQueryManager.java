package com.cloudskol.ifeel.trend;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.cloudskol.ifeel.common.TrendAggragationComparator;
import com.cloudskol.ifeel.db.FeelContentProvider;
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

    private static final TrendQueryManager instance = new TrendQueryManager();
    private TrendQueryManager() {}

    public static final synchronized TrendQueryManager getInstance() {
        return instance;
    }

    public List<TrendAggregationByFeeling> monthlyTrend(ContentResolver contentResolver) {
        Log.v(LOG_TAG, "Enters monthlyTrend()");
        final Range<String> monthlyRange = DateUtility.getInstance().getMonthlyRange();

        String[] columns = new String[] { "distinct feeling", "count(_id)" };

        String selection = "date between date(?) and date(?) GROUP BY feeling";
        String[] arguments = new String[] { monthlyRange.getStart(), monthlyRange.getEnd() };

        final Cursor cursor = contentResolver.query(FeelContentProvider.CONTENT_URI,
                columns,
                selection,
                arguments,
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


    public List<TrendAggregationByFeeling> weeklyTrend(ContentResolver contentResolver) {
        Log.v(LOG_TAG, "Enters weeklyTrend()");
        final Range<String> weeklyRange = DateUtility.getInstance().getWeeklyRange();

        String[] columns = new String[] { "distinct feeling", "count(_id)" };

        String selection = "date between date(?) and date(?) GROUP BY feeling";
        String[] arguments = new String[] { weeklyRange.getStart(), weeklyRange.getEnd() };

        final Cursor cursor = contentResolver.query(FeelContentProvider.CONTENT_URI, columns,
                selection,
                arguments,
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

    public List<TrendAggregationByFeeling> todaysTrend(ContentResolver contentResolver) {
        Log.v(LOG_TAG, "Enters todaysTrend with content resolver()");

        String[] columns = new String[] { "distinct feeling", "count(_id)" };

        String selection = "date=date(?) GROUP BY feeling";
        String[] arguments = new String[] { DateUtility.getInstance().getFormattedToday() };

        Cursor cursor = contentResolver.query(FeelContentProvider.CONTENT_URI,
                columns,
                selection,
                arguments,
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

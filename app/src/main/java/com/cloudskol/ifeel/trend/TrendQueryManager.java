package com.cloudskol.ifeel.trend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.db.FeelDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */

public class TrendQueryManager {
    private final String LOG_TAG = TrendQueryManager.class.getSimpleName();

    private static FeelDbHelper dbHelper;

    private static final TrendQueryManager instance = new TrendQueryManager();
    private TrendQueryManager() {}

    public static final TrendQueryManager getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new FeelDbHelper(context);
        }

        return instance;
    }

    public List<TodaysTrendAggregation> todaysTrend() {
        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] columns = new String[] { "distinct feeling", "count(_id)" };

        String selection = "date=?";
        String[] arguments = new String[] { "2016-11-02" };

        String groupBy = "feeling";

        final Cursor cursor = database.query(FeelContract.FeelEntry.TABLE_NAME,
                columns,
                selection,
                arguments,
                groupBy,
                null,
                null);

        cursor.moveToFirst();

        TodaysTrendAggregation trendAggregation = null;
        List<TodaysTrendAggregation> trendAggregations = new ArrayList<>(cursor.getCount());
        boolean hasData = true;
        while (hasData) {
            final String feeling = cursor.getString(cursor.getColumnIndex("feeling"));
            final int count = cursor.getInt(cursor.getColumnIndex("count(_id)"));

            trendAggregation = new TodaysTrendAggregation(feeling, count);
            trendAggregations.add(trendAggregation);
            Log.v(LOG_TAG, "Aggregation: " + feeling + " >> " + count);

            hasData = cursor.moveToNext();
        }

        Log.v(LOG_TAG, "Total size of the trendAggregations: " + trendAggregations.size());

        return trendAggregations;
    }
}

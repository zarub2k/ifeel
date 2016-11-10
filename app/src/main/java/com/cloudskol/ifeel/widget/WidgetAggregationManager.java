package com.cloudskol.ifeel.widget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cloudskol.ifeel.common.Tuple;
import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.db.FeelDbHelper;
import com.cloudskol.ifeel.feel.FeelingType;
import com.cloudskol.ifeel.util.DateUtility;

/**
 * @author tham
 */

public class WidgetAggregationManager {
    private static final String LOG_TAG = WidgetAggregationManager.class.getSimpleName();

    private static final WidgetAggregationManager instance = new WidgetAggregationManager();
    private WidgetAggregationManager() {}

    private static FeelDbHelper dbHelper;

    public static final synchronized WidgetAggregationManager getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new FeelDbHelper(context);
        }

        return instance;
    }

    public WidgetAggregation getAggregation() {
        Log.v(LOG_TAG, "Enters getAggregation()");
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

        Log.v(LOG_TAG, "Total number of results: " + cursor.getCount());

        int positiveCount = 0;
        int negativeCount = 0;

        boolean hasData = true;
        while (hasData && cursor.getCount() > 0) {
            final String feeling = cursor.getString(cursor.getColumnIndex("feeling"));
            final int count = cursor.getInt(cursor.getColumnIndex("count(_id)"));

            if (FeelingType.HAPPY.getName().equals(feeling) ||
                    FeelingType.RELAXED.getName().equals(feeling)) {
                positiveCount += count;

            } else if (FeelingType.SAD.getName().equals(feeling) ||
                    FeelingType.ANGRY.getName().equals(feeling)) {
                negativeCount += count;
            }

            hasData = cursor.moveToNext();
        }

        Log.v(LOG_TAG, "Positive count: " + positiveCount);
        Log.v(LOG_TAG, "Negative count: " + negativeCount);

        final Tuple positiveTuple = new Tuple("Positive", String.valueOf(positiveCount));
        final Tuple negativeTuple = new Tuple("Negative", String.valueOf(negativeCount));

        return new WidgetAggregation(positiveTuple, negativeTuple);
    }
}

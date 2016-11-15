package com.cloudskol.ifeel.influencer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cloudskol.ifeel.db.FeelContentProvider;
import com.cloudskol.ifeel.db.FeelContract;
import com.cloudskol.ifeel.db.FeelDbHelper;
import com.cloudskol.ifeel.util.DateUtility;
import com.cloudskol.ifeel.util.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */

public class InfluencerQueryManager {
    private static final String LOG_TAG = InfluencerQueryManager.class.getSimpleName();

    private static final InfluencerQueryManager instance = new InfluencerQueryManager();
    private InfluencerQueryManager() {}

    public static final synchronized InfluencerQueryManager getInstance() {
        return instance;
    }

    public List<InfluencerAggregation> getInfluencers(ContentResolver contentResolver) {
        Log.v(LOG_TAG, "Enters getPositiveInfluencers()");

        final Range<String> monthlyRange = DateUtility.getInstance().getMonthlyRange();

        String[] columns = new String[] { "distinct person", "feeling", "count(_id)" };

        String selection = "date between date(?) and date(?) GROUP BY person, feeling";
        String[] selectionArgs = new String[] { monthlyRange.getStart(), monthlyRange.getEnd() };

        final Cursor cursor = contentResolver.query(FeelContentProvider.CONTENT_URI,
                columns,
                selection,
                selectionArgs,
                null);
        cursor.moveToFirst();

        List<InfluencerAggregation> aggregations = new ArrayList<>(cursor.getCount());
        InfluencerAggregation aggregation = null;

        boolean hasData = true;
        while (hasData && cursor.getCount() > 0) {
            final String person = cursor.getString(cursor.getColumnIndex("person"));
            final String feeling = cursor.getString(cursor.getColumnIndex("feeling"));
            final int count = cursor.getInt(cursor.getColumnIndex("count(_id)"));

            aggregation = new InfluencerAggregation(person, feeling, count);
            aggregations.add(aggregation);
            Log.v(LOG_TAG, "Results: " + person + " > " + feeling + " > " + count);

            hasData = cursor.moveToNext();
        }

        closeCursor(cursor);

        return aggregations;
    }

    private void closeCursor(Cursor cursor) {
        if (cursor.isClosed()) {
            return;
        }

        cursor.close();
    }
}

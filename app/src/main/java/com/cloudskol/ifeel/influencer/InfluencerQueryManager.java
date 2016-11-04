package com.cloudskol.ifeel.influencer;

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

public class InfluencerQueryManager {
    private static final String LOG_TAG = InfluencerQueryManager.class.getSimpleName();

    private static FeelDbHelper dbHelper;

    private static final InfluencerQueryManager instance = new InfluencerQueryManager();
    private InfluencerQueryManager() {}

    public static final synchronized InfluencerQueryManager getInstance(Context context) {
        dbHelper = new FeelDbHelper(context);
        return instance;
    }

    public List<InfluencerAggregation> getInfluencers() {
        Log.v(LOG_TAG, "Enters getPositiveInfluencers()");

        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] columns = new String[] { "distinct person", "feeling", "count(_id)" };

        String selection = null;
        String[] slectionArgs = null;

        String groupBy = "person, feeling";

        final Cursor cursor = database.query(FeelContract.FeelEntry.TABLE_NAME,
                columns,
                selection,
                slectionArgs,
                groupBy,
                null,
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

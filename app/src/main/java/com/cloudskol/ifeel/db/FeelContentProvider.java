package com.cloudskol.ifeel.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * @author tham
 *
 * Content provider for iFeel application
 */
public class FeelContentProvider extends ContentProvider {
    private static final String LOG_TAG = FeelContentProvider.class.getSimpleName();
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int FEELINGS = 1;
    private static final int FEELINGS_BY_ID = 2;

    private static final String CONTENT_AUTHORITY = "com.cloudskol.ifeel";
    private static final String PATH_FEELINGS = "feelings";
    private static final String PATH_FEELINGS_BY_ID = "feelings/#";

    private static final String FEELINGS_URI = "content://" + CONTENT_AUTHORITY + "/" + PATH_FEELINGS;
    public static final Uri CONTENT_URI = Uri.parse(FEELINGS_URI);

    private SQLiteDatabase readableDb;
    private SQLiteDatabase writableDb;

    @Override
    public boolean onCreate() {
        Log.v(LOG_TAG, "Enters onCreate()");

        final FeelDbHelper feelDbHelper = new FeelDbHelper(getContext());
        readableDb = feelDbHelper.getReadableDatabase();
        writableDb = feelDbHelper.getWritableDatabase();

        return (readableDb != null) && (writableDb != null);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.v(LOG_TAG, "Enters query(): " + uri.toString());

        Cursor cursor = null;
        final int match = uriMatcher.match(uri);
        switch (match) {
            case FEELINGS:
            case FEELINGS_BY_ID:
                cursor = readableDb.query(FeelContract.FeelEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;

            default:
                break;
        }

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.v(LOG_TAG, "Enters insert(): " + uri.toString());

        final long insertedId = writableDb.insert(FeelContract.FeelEntry.TABLE_NAME, null, values);
        if (insertedId <= 0) {
            return null;
        }

        final Uri insertedUri = ContentUris.withAppendedId(CONTENT_URI, insertedId);
        getContext().getContentResolver().notifyChange(insertedUri, null);
        return insertedUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.v(LOG_TAG, "Enters update(): " + uri.toString());

        final int updateCount = writableDb.update(FeelContract.FeelEntry.TABLE_NAME, values,
                selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return updateCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(LOG_TAG, "Enters delete(): " + uri.toString());
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        String typeValue = null;
        final int match = uriMatcher.match(uri);
        switch (match) {
            case FEELINGS:
                typeValue = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "." + PATH_FEELINGS;
                break;

            case FEELINGS_BY_ID:
                typeValue = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "." + PATH_FEELINGS_BY_ID;
                break;
        }

        return typeValue;
    }

    static {
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_FEELINGS, FEELINGS);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_FEELINGS_BY_ID, FEELINGS_BY_ID);
    }
}

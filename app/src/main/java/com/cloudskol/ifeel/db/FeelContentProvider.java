package com.cloudskol.ifeel.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

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
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static {
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_FEELINGS, FEELINGS);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_FEELINGS_BY_ID, FEELINGS_BY_ID);
    }
}

package com.cloudskol.ifeel.db;

import android.provider.BaseColumns;

/**
 * @author tham
 */

public final class FeelContract {
    private FeelContract() {}

    public static class FeelEntry implements BaseColumns {
        public static final String TABLE_NAME = "feel";
        public static final String COLUMN_FEELING = "feeling";
        public static final String COLUMN_PERSON = "person";
        public static final String COLUMN_SUMMARY = "summary";
        public static final String COLUMN_DATE = "date";
    }
}

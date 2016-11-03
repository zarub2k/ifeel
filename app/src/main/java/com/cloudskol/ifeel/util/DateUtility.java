package com.cloudskol.ifeel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tham
 */

public class DateUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final DateUtility instance = new DateUtility();
    private DateUtility() {}

    public static final synchronized DateUtility getInstance() {
        return instance;
    }

    public String getFormattedDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    public String getFormattedToday() {
        return getFormattedDate(Calendar.getInstance().getTime());
    }
}

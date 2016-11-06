package com.cloudskol.ifeel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public Range<String> getWeeklyRange() {
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
        final String start = getFormattedDate(calendar.getTime());

        calendar.add(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_WEEK) - 1);
        final String end = getFormattedDate(calendar.getTime());

        return new Range<String>(start, end);
    }

    public Range<String> getMonthlyRange() {
        Calendar calendar = GregorianCalendar.getInstance();
//        System.out.println("Min: " + calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//        System.out.println("Max: " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        final String start = getFormattedDate(calendar.getTime());

        calendar.add(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        final String end = getFormattedDate(calendar.getTime());

        return new Range<String>(start, end);
    }
}

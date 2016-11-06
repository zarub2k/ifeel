package com.cloudskol.ifeel;

import com.cloudskol.ifeel.util.DateUtility;
import com.cloudskol.ifeel.util.Range;

import org.junit.Test;

/**
 * Created by tham on 03-11-2016.
 */

public class DateUtilityTest {
    @Test
    public void testGetFormattedToday() {
        final Range<String> weeklyRange = DateUtility.getInstance().getWeeklyRange();
        System.out.println("Start of the week: " + weeklyRange.getStart());
        System.out.println("End of the week: " + weeklyRange.getEnd());
    }

    @Test
    public void testGetMonthlyRange() {
        DateUtility.getInstance().getMonthlyRange();
    }
}

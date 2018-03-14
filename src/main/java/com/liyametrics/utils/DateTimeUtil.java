package com.liyametrics.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTimeUtil {

    public static String getDate(Period period) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(getDateDate(period));
    }

    public static Date getDateDate(Period period) {

        Calendar cal = Calendar.getInstance();

        switch (period) {
            case TODAY:
                break;
            case YESTERDAY:
                cal.add(Calendar.DATE, -1);
                break;
            case LAST_WEEK:
                cal.add(Calendar.DATE, -7);
                break;
            case LAST_MONTH:
                cal.add(Calendar.MONTH, -1);
                break;
            default:
                cal = Calendar.getInstance();
                break;
        }

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);

        return cal.getTime();
    }

    public static List<String> getRange(String from, String to) {

        DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime startDate = pattern.parseDateTime(from);
        DateTime endDate = pattern.parseDateTime(to);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        List<String> days = new ArrayList<>();

        while (startDate.isBefore(endDate)) {
            days.add(df.format(startDate.toDate()));
            startDate = startDate.plusDays(1);
        }

        return days;
    }

    public static Date fromString(String date) {
        DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime d = pattern.parseDateTime(date);

        return d.toDate();
    }

}

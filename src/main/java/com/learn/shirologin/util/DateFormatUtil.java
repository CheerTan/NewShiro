package com.learn.shirologin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static final SimpleDateFormat DATE_TIME_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDateByDateTime(String date) throws ParseException {
        return DATE_TIME_FORMAT.parse(date);
    }

    public static Date getDateByDate(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }

}

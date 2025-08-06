package com.quang.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class QTimeUtil {
    public static long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

}

package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

public class DateFactory {
    Date getDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return calendar.getTime();
    }
}

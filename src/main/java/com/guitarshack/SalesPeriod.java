package com.guitarshack;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DATE;
import static java.util.Calendar.YEAR;

public class SalesPeriod {
    private Date startDate;
    private Date endDate;

    void setHistoricalPeriod(Date date, int yearDiff, int startDateDiff, int endDateDiff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(YEAR, yearDiff);
        calendar.add(DATE, startDateDiff);
        startDate = calendar.getTime();
        calendar.add(DATE, endDateDiff);
        endDate = calendar.getTime();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}

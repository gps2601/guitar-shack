package com.guitarshack;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoricalSalesTest {

    @Test
    void weGetTotalSalesForAGivenDateRange() {
        DateFactory dateFactory = new DateFactory();
        Date startDate = dateFactory.getDate(2019, Calendar.JULY, 17);
        Date endDate = dateFactory.getDate(2019, Calendar.JULY, 27);

        HistoricalSales salesAPI = new HistoricalSalesAPI();

        assertEquals(salesAPI.total(811, startDate, endDate), 20);
    }
}

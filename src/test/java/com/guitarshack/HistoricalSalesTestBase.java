package com.guitarshack;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class HistoricalSalesTestBase {
    @Test
    void weGetTotalSalesForAGivenDateRange() {
        DateFactory dateFactory = new DateFactory();
        Date startDate = dateFactory.getDate(2019, Calendar.JULY, 17);
        Date endDate = dateFactory.getDate(2019, Calendar.JULY, 27);

        HistoricalSales salesAPI = new HistoricalSalesAPI(getRequester(), "https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/");

        assertEquals(salesAPI.total(811, startDate, endDate), 20);
    }

    protected abstract Requester getRequester();
}

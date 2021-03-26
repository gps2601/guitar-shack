package com.guitarshack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.AUGUST;
import static java.util.Calendar.JULY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RestockThresholdTest {
    private DateFactory dateFactory = null;

    @BeforeEach
    void setUp() {
        dateFactory = new DateFactory();
    }

    @Test
    void returnsTotalNumberOfHistoricalSalesInLeadTime() {
        HistoricalSales historicalSales = (productId, startDate, endDate) -> 5;
        Product product = new Product(449, "", 0, 0, 0, 21);
        Today today = () -> new Date();
        RestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);

        assertEquals(5, restockThreshold.thresholdFor(product));
    }

    @Test
    void providesCorrectStartDateWithMoreThanAYearsSales() {
        HistoricalSales historicalSales = mock(HistoricalSales.class);
        Date startDate = dateFactory.getDate(2019, JULY, 13);
        Date dateOfSale = dateFactory.getDate(2020, JULY, 13);
        Today today = () -> dateOfSale;
        RestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);
        Product product = new Product(449, "", 0, 0, 0, 21);
        restockThreshold.thresholdFor(product);

        verify(historicalSales).total(any(Integer.class), eq(startDate), any(Date.class));
    }

    @Test
    void providesCorrectEndDateWithMoreThanAYearsSales() {
        HistoricalSales historicalSales = mock(HistoricalSales.class);
        Date endDate = dateFactory.getDate(2019, AUGUST, 2);
        Date dateOfSale = dateFactory.getDate(2020, JULY, 13);
        Today today = () -> dateOfSale;
        RestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);
        Product product = new Product(449, "", 0, 0, 0, 21);

        restockThreshold.thresholdFor(product);

        verify(historicalSales).total(any(Integer.class), any(Date.class), eq(endDate));
    }

    @Test
    void providesCorrectStartDateWithLessThanAYearsSales() {
        HistoricalSales historicalSales = mock(HistoricalSales.class);
        when(historicalSales.total(any(Integer.class), any(Date.class), any(Date.class))).thenReturn(0, 5);
        Date dateOfSale = dateFactory.getDate(2019, AUGUST, 3);
        Date startDate = dateFactory.getDate(2019, JULY, 13);
        Today today = () -> dateOfSale;
        RestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);
        Product product = new Product(449, "", 0, 0, 0, 21);

        restockThreshold.thresholdFor(product);

        verify(historicalSales).total(any(Integer.class), eq(startDate), any(Date.class));
    }

    @Test
    void providesCorrectEndDateWithLessThanAYearsSale() {
        HistoricalSales historicalSales = mock(HistoricalSales.class);
        when(historicalSales.total(any(Integer.class), any(Date.class), any(Date.class))).thenReturn(0, 5);
        Date endDate = dateFactory.getDate(2019, AUGUST, 2);
        Date dateOfSale = dateFactory.getDate(2019, AUGUST, 3);
        Today today = () -> dateOfSale;
        HistoricalRestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);
        Product product = new Product(449, "", 0, 0, 0, 21);

        restockThreshold.thresholdFor(product);

        verify(historicalSales).total(any(Integer.class), any(Date.class), eq(endDate));
    }
}

package com.guitarshack.unit;

import com.guitarshack.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import static java.util.Calendar.AUGUST;
import static java.util.Calendar.JULY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestockThresholdTest {
    private static DateFactory dateFactory;

    @BeforeAll
    static void beforeAll() {
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
    void returnsTotalRecentSalesWhenHistoricalIsZero() {
        HistoricalSales historicalSales = mock(HistoricalSales.class);
        when(historicalSales.total(anyInt(), any(), any())).thenReturn(0, 5);
        Product product = new Product(449, "", 0, 0, 0, 21);
        Today today = () -> new Date();
        HistoricalRestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);

        assertEquals(5, restockThreshold.thresholdFor(product));
    }

    @Test
    void providesCorrectStartDateWithMoreThanAYearsSales() {
        HistoricalSales historicalSales = mock(HistoricalSales.class);
        when(historicalSales.total(any(Integer.class), any(Date.class), any(Date.class))).thenReturn(1);
        Date startDate = dateFactory.getDate(2019, JULY, 13);
        Date dateOfSale = dateFactory.getDate(2020, JULY, 13);
        Today today = () -> dateOfSale;
        RestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);
        Product product = new Product(449, "", 0, 0, 0, 21);
        restockThreshold.thresholdFor(product);

        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        verify(historicalSales).total(any(Integer.class), startDateCaptor.capture(), any(Date.class));

        assertDateEquals(startDate, startDateCaptor.getValue());
    }

    @Test
    void providesCorrectEndDateWithMoreThanAYearsSales() {
        HistoricalSales historicalSales = mock(HistoricalSales.class);
        when(historicalSales.total(any(Integer.class), any(Date.class), any(Date.class))).thenReturn(1);
        Date endDate = dateFactory.getDate(2019, AUGUST, 2);
        Date dateOfSale = dateFactory.getDate(2020, JULY, 13);
        Today today = () -> dateOfSale;
        RestockThreshold restockThreshold = new HistoricalRestockThreshold(historicalSales, today);
        Product product = new Product(449, "", 0, 0, 0, 21);

        restockThreshold.thresholdFor(product);

        ArgumentCaptor<Date> endDateCaptor = ArgumentCaptor.forClass(Date.class);

        verify(historicalSales).total(any(Integer.class), any(Date.class), endDateCaptor.capture());

        assertDateEquals(endDate, endDateCaptor.getValue());
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

        ArgumentCaptor<Date> startDateCaptor = ArgumentCaptor.forClass(Date.class);
        verify(historicalSales, times(2)).total(any(Integer.class), startDateCaptor.capture(), any(Date.class));

        Date date = startDateCaptor.getAllValues().get(1);
        assertDateEquals(startDate, date);
    }

    private void assertDateEquals(Date startDate, Date date) {
        assertEquals(startDate.getDate(), date.getDate());
        assertEquals(startDate.getMonth(), date.getMonth());
        assertEquals(startDate.getYear(), date.getYear());
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
        ArgumentCaptor<Date> endDateCaptor = ArgumentCaptor.forClass(Date.class);

        verify(historicalSales, times(2)).total(any(Integer.class), any(Date.class), endDateCaptor.capture());

        Date actualEndDate = endDateCaptor.getAllValues().get(1);
        assertDateEquals(endDate, actualEndDate);
    }
}

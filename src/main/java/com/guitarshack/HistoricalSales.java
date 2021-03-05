package com.guitarshack;

import java.util.Date;

public interface HistoricalSales {
    int total(int productId, Date startDate, Date endDate);
}

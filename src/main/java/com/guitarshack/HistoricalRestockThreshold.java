package com.guitarshack;

import java.util.Date;

public class HistoricalRestockThreshold implements RestockThreshold {
    private static final int LAST_YEAR = -1;
    private static final int SAME_DATE = 0;
    private static final int THIS_YEAR = 0;
    private final HistoricalSales historicalSales;
    private final Today today;
    private final SalesDateRange salesDateRange = new SalesDateRange();

    public HistoricalRestockThreshold(HistoricalSales historicalSales, Today today) {
        this.historicalSales = historicalSales;
        this.today = today;
    }

    @Override
    public int thresholdFor(Product product) {
        Date date = today.get();
        int leadTime = product.getLeadTime();
        int inclusiveLeadTime = leadTime - 1;
        salesDateRange.setRange(date, LAST_YEAR, SAME_DATE, inclusiveLeadTime);

        int salesLastYear = historicalSales.total(product.getId(), salesDateRange.getStartDate(), salesDateRange.getEndDate());

        if (salesLastYear == 0) {
            salesDateRange.setRange(date, THIS_YEAR, -leadTime, inclusiveLeadTime);
            return historicalSales.total(product.getId(), salesDateRange.getStartDate(), salesDateRange.getEndDate());
        } else {
            return salesLastYear;
        }
    }
}

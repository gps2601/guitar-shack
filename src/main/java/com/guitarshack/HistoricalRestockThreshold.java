package com.guitarshack;

import java.util.Date;

public class HistoricalRestockThreshold implements RestockThreshold {
    public static final int LAST_YEAR = -1;
    public static final int SAME_DATE = 0;
    public static final int THIS_YEAR = 0;
    private final HistoricalSales historicalSales;
    private final Today today;
    private final SalesPeriod salesPeriod = new SalesPeriod();

    public HistoricalRestockThreshold(HistoricalSales historicalSales, Today today) {
        this.historicalSales = historicalSales;
        this.today = today;
    }

    @Override
    public int thresholdFor(Product product) {
        Date date = today.get();
        int leadTime = product.getLeadTime();
        int inclusiveLeadTime = leadTime - 1;
        salesPeriod.setHistoricalPeriod(date, LAST_YEAR, SAME_DATE, inclusiveLeadTime);

        int salesLastYear = historicalSales.total(product.getProductId(), salesPeriod.getStartDate(), salesPeriod.getEndDate());

        if (salesLastYear == 0) {
            salesPeriod.setHistoricalPeriod(date, THIS_YEAR, -leadTime, inclusiveLeadTime);
            return historicalSales.total(product.getProductId(), salesPeriod.getStartDate(), salesPeriod.getEndDate());
        } else {
            return salesLastYear;
        }
    }
}

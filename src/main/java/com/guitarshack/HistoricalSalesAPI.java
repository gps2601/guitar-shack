package com.guitarshack;

import retrofit2.Call;
import retrofit2.Retrofit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricalSalesAPI implements HistoricalSales {
    private final Requester<TotalSales> requester;
    private final String baseUrl;

    public HistoricalSalesAPI(Requester<TotalSales> requester, String baseUrl) {
        this.requester = requester;
        this.baseUrl = baseUrl;
    }

    @Override
    public int total(int productId, Date startDate, Date endDate) {
        Retrofit retrofit = new Retrofitter().getRetrofit(baseUrl);
        SalesData salesData = retrofit.create(SalesData.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy");
        Call<TotalSales> totalSalesCall = salesData.totalSales(productId, simpleDateFormat.format(startDate), simpleDateFormat.format(endDate));
        try {
            TotalSales totalSales = requester.execute(totalSalesCall);
            return totalSales.total;
        } catch (Exception e) {
        }
        return 0;
    }
}

package com.guitarshack;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricalSalesAPI implements HistoricalSales {
    private final Requester requester;

    public HistoricalSalesAPI(Requester requester) {
        this.requester = requester;
    }

    @Override
    public int total(int productId, Date startDate, Date endDate) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        SalesData salesData = retrofit.create(SalesData.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy");
        Call<TotalSales> totalSalesCall = salesData.totalSale(productId, simpleDateFormat.format(startDate), simpleDateFormat.format(endDate));
        try {
            TotalSales totalSales = requester.execute(totalSalesCall);
            return totalSales.total;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
//https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales?productId=811&startDate=7%2F17%2F2019&endDate=7%2F27%2F2019

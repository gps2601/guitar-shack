package com.guitarshack;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class WebRequester implements Requester {
    public WebRequester() {
    }

    @Override
    public TotalSales execute(Call<TotalSales> totalSalesCall) throws IOException {
        Response<TotalSales> execute = totalSalesCall.execute();
        return execute.body();
    }
}

package com.guitarshack;

import retrofit2.Call;

import java.io.IOException;

public interface Requester {
    TotalSales execute(Call<TotalSales> totalSalesCall) throws IOException;
}

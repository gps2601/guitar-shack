package com.guitarshack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesData {
    @GET("sales?action=total")
    Call<TotalSales> totalSales(@Query("productId") int productId, @Query("startDate") String startDate, @Query("endDate") String endDate);
}

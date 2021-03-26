package com.guitarshack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductData {
    @GET("product?")
    Call<Product> getProduct(@Query("id") int productId);
}

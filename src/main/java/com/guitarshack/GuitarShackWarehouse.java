package com.guitarshack;

import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

public class GuitarShackWarehouse implements Warehouse {
    private final Requester<Product> requester;
    private final String baseUrl;

    public GuitarShackWarehouse(Requester<Product> requester, String baseUrl) {
        this.requester = requester;
        this.baseUrl = baseUrl;
    }

    @Override
    public Product findProduct(int productId) {
        Retrofit retrofit = new Retrofitter().getRetrofit(baseUrl);
        ProductData productData = retrofit.create(ProductData.class);
        Call<Product> product = productData.getProduct(productId);
        try {
            return requester.execute(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

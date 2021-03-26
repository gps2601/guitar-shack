package com.guitarshack;

import retrofit2.Call;

import java.io.IOException;

public class WarehouseUnitTest extends WarehouseTestBase {

    @Override
    protected Requester<Product> getRequester() {
        Requester<Product> requester = new Requester<Product>() {
            @Override
            public Product execute(Call<Product> call) throws IOException {
                return new Product(811, "", 0, 0, 0, 0);
            }
        };
        return requester;
    }
}

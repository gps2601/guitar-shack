package com.guitarshack;

import retrofit2.Call;

import java.io.IOException;

public class HistoricalSalesUnitTest extends HistoricalSalesTestBase {
    @Override
    protected Requester getRequester() {

        return new Requester<TotalSales>() {
            @Override
            public TotalSales execute(Call<TotalSales> call) throws IOException {
                return new TotalSales(20);
            }
        };
    }
}

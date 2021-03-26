package com.guitarshack;

public class WarehouseIntegrationTest extends WarehouseTestBase {
    @Override
    protected Requester<Product> getRequester() {
        return new WebRequester<Product>();
    }
}

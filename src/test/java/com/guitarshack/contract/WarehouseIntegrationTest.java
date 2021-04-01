package com.guitarshack.contract;

import com.guitarshack.Product;
import com.guitarshack.Requester;
import com.guitarshack.WarehouseTestBase;
import com.guitarshack.WebRequester;

public class WarehouseIntegrationTest extends WarehouseTestBase {
    @Override
    protected Requester<Product> getRequester() {
        return new WebRequester<Product>();
    }
}

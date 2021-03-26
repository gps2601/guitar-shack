package com.guitarshack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class WarehouseTestBase {
    @Test
    void warehouseFetchesProductInformation() {
        Warehouse warehouse = new GuitarShackWarehouse(getRequester(), "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/");
        Product fetchedProduct = warehouse.findProduct(811);
        assertEquals(811, fetchedProduct.getId());
    }

    protected abstract Requester<Product> getRequester();
}

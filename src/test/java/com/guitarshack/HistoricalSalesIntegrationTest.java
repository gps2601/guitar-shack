package com.guitarshack;

public class HistoricalSalesIntegrationTest extends HistoricalSalesTestBase {

    @Override
    protected Requester getRequester() {
        return new WebRequester();
    }
}

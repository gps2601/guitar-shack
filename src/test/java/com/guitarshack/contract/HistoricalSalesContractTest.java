package com.guitarshack.contract;

import com.guitarshack.HistoricalSalesTestBase;
import com.guitarshack.Requester;
import com.guitarshack.WebRequester;

public class HistoricalSalesContractTest extends HistoricalSalesTestBase {

    @Override
    protected Requester getRequester() {
        return new WebRequester();
    }
}

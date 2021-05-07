package com.guitarshack;

import java.util.List;

public class OrderHandler {
    private final RestockChecker restockChecker;

    public OrderHandler(RestockChecker restockChecker) {
        this.restockChecker = restockChecker;
    }

    public void onSale(List<Order> orders) {
        orders.forEach(restockChecker::onSale);
    }
}

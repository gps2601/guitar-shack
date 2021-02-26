package com.guitarshack;

public class RestockChecker {
    private final ReorderNotifier reorderNotifier;

    public RestockChecker(ReorderNotifier reorderNotifier, Warehouse warehouse, RestockThreshold restockThreshold) {
        this.reorderNotifier = reorderNotifier;
    }

    public void onSale(int productId, int quantitySold) {
        reorderNotifier.send("Please reorder product 449 (Fender Deluxe Nashville Telecaster MN in 2 Colour Sunburst), Minimum order: 5, Rack space: 10");
    }
}

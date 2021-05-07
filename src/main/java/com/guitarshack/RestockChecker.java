package com.guitarshack;

import java.util.List;

public class RestockChecker {
    private final ReorderNotifier reorderNotifier;
    private final Warehouse warehouse;
    private final RestockThreshold restockThreshold;

    public RestockChecker(ReorderNotifier reorderNotifier, Warehouse warehouse, RestockThreshold restockThreshold) {
        this.reorderNotifier = reorderNotifier;
        this.warehouse = warehouse;
        this.restockThreshold = restockThreshold;
    }

    public void onSale(Order order) {
        Product product = warehouse.findProduct(order.productId);
        int restockThreshold = this.restockThreshold.thresholdFor(product);
        if (needsRestocking(order.quantitySold, product, restockThreshold) && !alreadyNotified(product, restockThreshold)) {
            reorderNotifier.send(product.formatNotification());
        }
    }

    public void onSale(List<Order> orders) {
        orders.forEach(this::onSale);
    }

    private boolean needsRestocking(int quantitySold, Product product, int restockThreshold) {
        return product.getStock() - quantitySold <= restockThreshold;
    }

    private boolean alreadyNotified(Product product, int restockThreshold) {
        return product.getStock() <= restockThreshold;
    }
}

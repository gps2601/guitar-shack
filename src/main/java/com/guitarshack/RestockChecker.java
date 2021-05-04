package com.guitarshack;

public class RestockChecker {
    private final ReorderNotifier reorderNotifier;
    private final Warehouse warehouse;
    private final RestockThreshold restockThreshold;

    public RestockChecker(ReorderNotifier reorderNotifier, Warehouse warehouse, RestockThreshold restockThreshold) {
        this.reorderNotifier = reorderNotifier;
        this.warehouse = warehouse;
        this.restockThreshold = restockThreshold;
    }

    public void onSale(int productId, int quantitySold) {
        Product product = warehouse.findProduct(productId);
        int restockThreshold = this.restockThreshold.thresholdFor(product);
        if (needsRestocking(quantitySold, product, restockThreshold) && !alreadyNotified(product, restockThreshold)) {
            reorderNotifier.send(product.formatNotification());
        }
    }

    private boolean needsRestocking(int quantitySold, Product product, int restockThreshold) {
        return product.getStock() - quantitySold <= restockThreshold;
    }

    private boolean alreadyNotified(Product product, int restockThreshold) {
        return product.getStock() <= restockThreshold;
    }
}

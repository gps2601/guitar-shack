package com.guitarshack;

public class Product {
    private final int productId;
    private final String description;
    private final int stock;

    private final int rackSpace;

    private final int minimumOrder;

    private final int leadTime;

    public Product(int productId, String description, int stock, int rackSpace, int minimumOrder, int leadTime) {
        this.productId = productId;
        this.description = description;
        this.stock = stock;
        this.rackSpace = rackSpace;
        this.minimumOrder = minimumOrder;
        this.leadTime = leadTime;
    }

    String formatNotificationFor() {
        return String.format("Please reorder product %s (%s), Minimum order: %d, Rack space: %d",
                productId,
                description,
                minimumOrder,
                rackSpace
        );
    }

    public int getStock() {
        return stock;
    }

    public int getProductId() {
        return productId;
    }

    public int getLeadTime() {
        return leadTime;
    }
}

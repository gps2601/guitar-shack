package com.guitarshack;

import static java.lang.String.format;

public class Product {
    public int id;
    public String description;
    public int stock;
    public int rackspace;
    public int minOrder;
    public int leadTime;

    public Product(int id, String description, int stock, int rackspace, int minOrder, int leadTime) {
        this.id = id;
        this.description = description;
        this.stock = stock;
        this.rackspace = rackspace;
        this.minOrder = minOrder;
        this.leadTime = leadTime;
    }

    String formatNotification() {
        return format("Please reorder product %s (%s), Minimum order: %d, Rack space: %d",
                id,
                description,
                minOrder,
                rackspace
        );
    }

    public int getStock() {
        return stock;
    }

    public int getId() {
        return id;
    }

    public int getLeadTime() {
        return leadTime;
    }
}

package com.guitarshack;

public class Order {
    public int productId;
    public int quantitySold;

    public Order(int productId, int quantitySold) {
        this.productId = productId;
        this.quantitySold = quantitySold;
    }

    public Order() {}
}

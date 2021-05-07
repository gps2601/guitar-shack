package com.guitarshack;

import io.javalin.Javalin;

import java.util.Date;

public class Entrypoint {
    private static final RestockChecker restockChecker = RestockCheckerBuilder.build(new TwilioReorderNotifier(), Date::new);
    private static final OrderHandler orderHandler = new OrderHandler(restockChecker);

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(Integer.parseInt(System.getenv("PORT")));
        app.post("/order", ctx -> {
            Order order = ctx.bodyAsClass(Order.class);
            restockChecker.onSale(order);
            ctx.status(204);
        });

        app.post("/orders", ctx -> {
           OrderList orderList = ctx.bodyAsClass(OrderList.class);
           orderHandler.onSale(orderList.orders);
        });
    }
}

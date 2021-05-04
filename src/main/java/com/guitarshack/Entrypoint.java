package com.guitarshack;

import io.javalin.Javalin;

import java.util.Date;

public class Entrypoint {
    private static final RestockChecker restockChecker = RestockCheckerBuilder.build(new TwilioReorderNotifier(), Date::new);

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.post("/order", ctx -> {
            Order order = ctx.bodyAsClass(Order.class);
            restockChecker.onSale(order.productId, order.quantitySold);
            ctx.status(204);
        });
    }
}

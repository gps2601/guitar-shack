package com.guitarshack;

import org.jetbrains.annotations.NotNull;

public class RestockCheckerBuilder {
    @NotNull
    public static RestockChecker build(ReorderNotifier notifier, Today today) {
        return new RestockChecker(
                notifier,
                new GuitarShackWarehouse(
                        new WebRequester<>(),
                        "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/"
                ),
                new HistoricalRestockThreshold(
                        new HistoricalSalesAPI(
                                new WebRequester<>(),
                                "https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/"
                        ),
                        today
                )
        );
    }
}

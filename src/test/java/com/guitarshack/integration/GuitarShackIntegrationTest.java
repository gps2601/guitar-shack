package com.guitarshack.integration;

import com.guitarshack.*;
import org.junit.jupiter.api.Test;

import static java.util.Calendar.JULY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GuitarShackIntegrationTest {
    @Test
    void shouldSendReorderNotification() {
        ReorderNotifier reorderNotifier = mock(ReorderNotifier.class);
        Today dateStub = () -> {
            DateFactory dateFactory = new DateFactory();
            return dateFactory.getDate(2020, JULY, 17);
        };

        RestockChecker restockChecker = RestockCheckerBuilder.build(reorderNotifier, dateStub);

        restockChecker.onSale(811, 31);

        verify(reorderNotifier).send(any());
    }
}

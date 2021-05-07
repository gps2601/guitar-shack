package com.guitarshack;

import org.junit.jupiter.api.Test;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderHandlerTest {

    @Test
    void shouldSendReorderNotificationsForMultipleOrders() {
        RestockChecker restockChecker = mock(RestockChecker.class);
        OrderHandler orderHandler = new OrderHandler(restockChecker);

        orderHandler.onSale(of(new Order(811, 31), new Order(374, 100)));

        verify(restockChecker, times(2)).onSale(any());
    }
}

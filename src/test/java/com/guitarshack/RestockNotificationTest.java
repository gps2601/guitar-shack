package com.guitarshack;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RestockNotificationTest {

    @Test
    void salesQuantityTakesProductStockBelowThreshold() {
        int quantitySold = 1;
        String description = "Fender Deluxe Nashville Telecaster MN in 2 Colour Sunburst";
        int stock = 5;
        int rackSpace = 10;
        int minimumOrder = 5;
        int productId = 449;
        Product product = new Product(productId, description, stock, rackSpace, minimumOrder, 0);

        ReorderNotifier reorderNotifier = mock(ReorderNotifier.class);

        Warehouse warehouse = productId1 -> product;
        RestockThreshold restockThreshold = product1 -> 4;

        RestockChecker restockChecker = new RestockChecker(reorderNotifier, warehouse, restockThreshold);
        restockChecker.onSale(productId, quantitySold);

        verify(reorderNotifier).send("Please reorder product 449 (Fender Deluxe Nashville Telecaster MN in 2 Colour Sunburst), Minimum order: 5, Rack space: 10");
    }

    @Test
    void salesQuantityDoesNotTakeProductBelowThreshold() {
        int productId = 449;
        int stock = 5;
        int quantitySold = 1;
        ReorderNotifier reorderNotifier = mock(ReorderNotifier.class);
        Product product = new Product(productId, "", stock, 0, 0, 0);
        Warehouse warehouse = productId1 -> product;
        RestockThreshold restockThreshold = product1 -> 3;
        RestockChecker restockChecker = new RestockChecker(reorderNotifier, warehouse, restockThreshold);

        restockChecker.onSale(productId, quantitySold);

        verifyNoInteractions(reorderNotifier);
    }

    @Test
    void stockIsAlreadyBelowThreshold() {
        int productId = 449;
        int stock = 5;
        int quantitySold = 1;
        ReorderNotifier reorderNotifier = mock(ReorderNotifier.class);
        Product product = new Product(productId, "", stock, 0, 0, 0);
        Warehouse warehouse = productId1 -> product;
        RestockThreshold restockThreshold = product1 -> 6;
        RestockChecker restockChecker = new RestockChecker(reorderNotifier, warehouse, restockThreshold);

        restockChecker.onSale(productId, quantitySold);

        verifyNoInteractions(reorderNotifier);
    }
}

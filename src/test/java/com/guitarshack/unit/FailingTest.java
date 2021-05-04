package com.guitarshack.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FailingTest {
    @Test
    void name() {
        Assertions.fail();
    }
}

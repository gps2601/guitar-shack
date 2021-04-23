package com.guitarshack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FailingTest {
    @Test
    void name() {
        fail();
    }
}

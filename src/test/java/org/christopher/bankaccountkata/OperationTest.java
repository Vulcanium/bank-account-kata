package org.christopher.bankaccountkata;

import org.christopher.bankaccountkata.Operation.OperationType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {


    @Test
    public void operationShouldStoreAmountAndBalanceCorrectly() {
        Operation op = new Operation(LocalDate.of(2026, 1, 15),
                100,
                100,
                OperationType.DEPOSIT);

        assertEquals("2026-01-15", op.date().toString());
        assertEquals(100, op.amount());
        assertEquals(100, op.balance());
        assertEquals(OperationType.DEPOSIT, op.operationType());
    }

    @Test
    public void operationShouldBeImmutable() {
        Operation op = new Operation(LocalDate.of(2026, 1, 15),
                200,
                200,
                OperationType.WITHDRAWAL);

        assertAll(
                () -> assertEquals("2026-01-15", op.date().toString()),
                () -> assertEquals(200, op.amount()),
                () -> assertEquals(200, op.balance()),
                () -> assertEquals(OperationType.WITHDRAWAL, op.operationType())
        );
    }
}

package org.christopher.bankaccountkata;

import org.christopher.bankaccountkata.Operation.OperationType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountStatementPrinterTest {

    @Test
    public void shouldPrintEmptyAccountStatementWhenNoOperations() {
        AccountStatementPrinter printer = new AccountStatementPrinter();
        List<Operation> operations = List.of();

        String accountStatement = printer.print(operations);

        assertEquals("Date | Amount | Balance\n", accountStatement);
    }

    @Test
    public void shouldFormatAccountStatementCorrectly() {
        AccountStatementPrinter printer = new AccountStatementPrinter();
        List<Operation> operations = List.of(
                new Operation(LocalDate.of(2026, 1, 15),
                        100,
                        100,
                        OperationType.DEPOSIT),
                new Operation(LocalDate.of(2026, 1, 16),
                        40,
                        60,
                        OperationType.WITHDRAWAL)
        );

        String accountStatement = printer.print(operations);

        String expectedAccountStatement = """
                Date | Amount | Balance
                2026-01-15 | 100.0 | 100.0
                2026-01-16 | -40.0 | 60.0
                """;

        assertEquals(expectedAccountStatement, accountStatement);
    }
}

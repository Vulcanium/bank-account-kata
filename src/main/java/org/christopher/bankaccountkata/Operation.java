package org.christopher.bankaccountkata;

import java.time.LocalDate;

// Ensures the immutability of an operation
public record Operation(LocalDate date, double amount, double balance, OperationType operationType) {
    public enum OperationType {DEPOSIT, WITHDRAWAL}
}

package org.christopher.bankaccountkata;

import lombok.Getter;
import org.christopher.bankaccountkata.Operation.OperationType;
import org.christopher.bankaccountkata.customexceptions.InsufficientAccountBalanceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountStatement {

    private double accountBalance;
    private final List<Operation> operationHistory;

    public AccountStatement() {
        this.accountBalance = 0;
        this.operationHistory = new ArrayList<>();
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.accountBalance += amount;
        addOperationHistory(new Operation(LocalDate.now(), amount, accountBalance, OperationType.DEPOSIT));
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (amount > accountBalance) {
            throw new InsufficientAccountBalanceException("Withdrawal impossible: insufficient balance");
        }

        this.accountBalance -= amount;
        addOperationHistory(new Operation(LocalDate.now(), amount, accountBalance, OperationType.WITHDRAWAL));
    }

    private void addOperationHistory(Operation operation) {
        operationHistory.add(operation);
    }

}

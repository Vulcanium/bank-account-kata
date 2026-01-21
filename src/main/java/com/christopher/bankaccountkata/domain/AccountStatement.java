package com.christopher.bankaccountkata.domain;

import com.christopher.bankaccountkata.customexceptions.InsufficientAccountBalanceException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class AccountStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private double accountBalance;

    @OneToMany(mappedBy = "accountStatement", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<@Valid Operation> operationHistory;

    public AccountStatement() {
        this.accountBalance = 0;
        this.operationHistory = new ArrayList<>();
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.accountBalance += amount;
        addOperationHistory(new Operation(this, LocalDate.now(), amount, accountBalance));
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (amount > accountBalance) {
            throw new InsufficientAccountBalanceException("Withdrawal impossible: insufficient balance");
        }

        this.accountBalance -= amount;
        addOperationHistory(new Operation(this, LocalDate.now(), -amount, accountBalance));
    }

    private void addOperationHistory(Operation operation) {
        operationHistory.add(operation);
    }

}

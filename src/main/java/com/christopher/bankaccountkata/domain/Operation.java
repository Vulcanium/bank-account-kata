package com.christopher.bankaccountkata.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Double amount;

    @NotNull
    @PositiveOrZero
    private Double balance;

    @ManyToOne(optional = false)
    private AccountStatement accountStatement;

    public Operation(AccountStatement accountStatement, LocalDate date, Double amount, Double balance) {
        this.accountStatement = accountStatement;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }
}

package com.christopher.bankaccountkata.controller;

import com.christopher.bankaccountkata.domain.AccountStatement;
import com.christopher.bankaccountkata.printer.AccountStatementPrinter;
import com.christopher.bankaccountkata.service.AccountStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountStatementController {

    private final AccountStatementService service;
    private final AccountStatementPrinter printer;

    @PostMapping
    public AccountStatement createAccount() {
        return service.createAccount();
    }

    @PostMapping("/deposit/{id}")
    public void deposit(@PathVariable Long id, @RequestParam double amount) {
        service.deposit(id, amount);
    }

    @PostMapping("/withdraw/{id}")
    public void withdraw(@PathVariable Long id, @RequestParam double amount) {
        service.withdraw(id, amount);
    }

    @GetMapping("/statement/{id}")
    public String statement(@PathVariable Long id) {
        return printer.print(service.getOperationHistory(id));
    }
}

package com.christopher.bankaccountkata.service;

import com.christopher.bankaccountkata.domain.AccountStatement;
import com.christopher.bankaccountkata.domain.Operation;
import com.christopher.bankaccountkata.repository.AccountStatementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountStatementService {

    private final AccountStatementRepository repository;

    public AccountStatement createAccount() {
        return repository.save(new AccountStatement());
    }

    public void deposit(Long accountId, double amount) {
        AccountStatement account = repository.findById(accountId).orElseThrow();
        account.deposit(amount);
    }

    public void withdraw(Long accountId, double amount) {
        AccountStatement account = repository.findById(accountId).orElseThrow();
        account.withdraw(amount);
    }

    public List<Operation> getOperationHistory(Long accountId) {
        return repository.findById(accountId).orElseThrow().getOperationHistory();
    }
}

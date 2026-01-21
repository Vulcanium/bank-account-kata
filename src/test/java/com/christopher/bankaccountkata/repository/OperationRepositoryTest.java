package com.christopher.bankaccountkata.repository;

import com.christopher.bankaccountkata.domain.AccountStatement;
import com.christopher.bankaccountkata.domain.Operation;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AllArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class OperationRepositoryTest {

    private OperationRepository operationRepository;
    private AccountStatementRepository accountStatementRepository;

    @Test
    void shouldSaveAndRetrieveOperation() {

        // Create an account
        AccountStatement account = new AccountStatement();
        accountStatementRepository.save(account);

        // Create an operation
        Operation operation = new Operation(
                account,
                LocalDate.of(2026, 1, 21),
                100.0,
                100.0);

        // Persist the operation
        operationRepository.save(operation);

        // Check that it's correctly in the database
        List<Operation> operations = operationRepository.findByAccountStatementId(account.getId());

        // Asserts
        assertEquals(1, operations.size());
        Operation retrieved = operations.getFirst();
        assertEquals("2026-01-21", retrieved.getDate().toString());
        assertEquals(100, retrieved.getAmount());
        assertEquals(100, retrieved.getBalance());
        assertEquals(account.getId(), retrieved.getAccountStatement().getId());
    }

    @Test
    void shouldSaveMultipleOperationsForSameAccount() {
        AccountStatement account = new AccountStatement();
        accountStatementRepository.save(account);

        Operation op1 = new Operation(account, LocalDate.now(), 100.0, 100.0);
        Operation op2 = new Operation(account, LocalDate.now(), -50.0, 50.0);

        operationRepository.saveAll(List.of(op1, op2));

        List<Operation> operations = operationRepository.findByAccountStatementId(account.getId());
        assertEquals(2, operations.size());
    }
}


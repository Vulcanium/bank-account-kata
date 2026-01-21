package com.christopher.bankaccountkata.service;

import com.christopher.bankaccountkata.customexceptions.InsufficientAccountBalanceException;
import com.christopher.bankaccountkata.domain.AccountStatement;
import com.christopher.bankaccountkata.domain.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class AccountStatementServiceTest {

    private final AccountStatementService service;

    @Test
    public void balanceShouldBeZeroForNewAccount() {
        AccountStatement account = service.createAccount();

        assertNotNull(account.getId());
        assertEquals(0, account.getAccountBalance());
        assertTrue(account.getOperationHistory().isEmpty());
    }

    @Test
    public void depositShouldIncreaseBalanceAndAddNewOperationHistory() {
        AccountStatement account = service.createAccount();
        Long accountId = account.getId();

        service.deposit(accountId, 200);

        List<Operation> operationHistory = service.getOperationHistory(accountId);
        assertEquals(1, operationHistory.size());
        assertEquals(200, operationHistory.getFirst().getAmount());
        assertEquals(200, operationHistory.getFirst().getBalance());
    }

    @Test
    public void withdrawShouldDecreaseBalanceAndAddNewOperationHistory() {
        AccountStatement account = service.createAccount();
        Long accountId = account.getId();

        service.deposit(accountId, 200);
        service.withdraw(accountId, 50);

        List<Operation> operationHistory = service.getOperationHistory(accountId);
        assertEquals(2, operationHistory.size());
        assertEquals(-50, operationHistory.get(1).getAmount());
        assertEquals(150, operationHistory.get(1).getBalance());
    }

    @Test
    public void withdrawMoreThanBalanceShouldThrowException() {
        AccountStatement account = service.createAccount();
        Long accountId = account.getId();

        service.deposit(accountId, 50);

        assertThrows(InsufficientAccountBalanceException.class, () -> service.withdraw(accountId, 100));

        // The balance and the operation history should remain unchanged after a failed withdrawal
        List<Operation> operationHistory = service.getOperationHistory(accountId);
        assertEquals(1, operationHistory.size());
        assertEquals(50, operationHistory.getFirst().getBalance());
    }

    @Test
    public void negativeDepositAmountShouldThrowException() {
        AccountStatement account = service.createAccount();
        Long accountId = account.getId();

        assertThrows(IllegalArgumentException.class, () -> service.deposit(accountId, -50));

        // The balance and the operation history should remain unchanged after a failed deposit
        assertEquals(0, account.getAccountBalance());
        assertTrue(account.getOperationHistory().isEmpty());
    }

    @Test
    public void negativeWithdrawAmountShouldThrowException() {
        AccountStatement account = service.createAccount();
        Long accountId = account.getId();

        assertThrows(IllegalArgumentException.class, () -> service.withdraw(accountId, -100));

        // The balance and the operation history should remain unchanged after a failed withdrawal
        assertEquals(0, account.getAccountBalance());
        assertTrue(account.getOperationHistory().isEmpty());
    }

}

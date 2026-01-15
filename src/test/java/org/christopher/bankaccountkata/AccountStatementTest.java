package org.christopher.bankaccountkata;

import org.christopher.bankaccountkata.customexceptions.InsufficientAccountBalanceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountStatementTest {

    @Test
    public void balanceShouldBeZeroForNewAccount() {
        AccountStatement account = new AccountStatement();

        assertEquals(0, account.getAccountBalance());
    }

    @Test
    public void operationHistoryShouldBeEmptyForNewAccount() {
        AccountStatement account = new AccountStatement();

        assertEquals(0, account.getOperationHistory().size());
    }

    @Test
    public void depositShouldIncreaseBalance() {
        AccountStatement account = new AccountStatement();
        account.deposit(100);

        assertEquals(100, account.getAccountBalance());
    }

    @Test
    public void depositShouldAddNewOperationHistory() {
        AccountStatement account = new AccountStatement();
        account.deposit(100);

        assertEquals(1, account.getOperationHistory().size());
    }

    @Test
    public void withdrawShouldDecreaseBalance() {
        AccountStatement account = new AccountStatement();
        account.deposit(100);
        account.withdraw(40);

        assertEquals(60, account.getAccountBalance());
    }

    @Test
    public void withdrawShouldAddNewOperationHistory() {
        AccountStatement account = new AccountStatement();
        account.deposit(100);
        account.withdraw(40);

        assertEquals(2, account.getOperationHistory().size());
    }

    @Test
    public void withdrawMoreThanBalanceShouldThrowException() {
        AccountStatement account = new AccountStatement();
        account.deposit(50);

        assertThrows(InsufficientAccountBalanceException.class, () -> account.withdraw(100));

        // The balance and the operation history should remain unchanged after a failed withdrawal
        assertEquals(50, account.getAccountBalance());
        assertEquals(1, account.getOperationHistory().size());
    }

    @Test
    public void negativeDepositAmountShouldThrowException() {
        AccountStatement account = new AccountStatement();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));

        // The balance and the operation history should remain unchanged after a failed deposit
        assertEquals(0, account.getAccountBalance());
        assertEquals(0, account.getOperationHistory().size());
    }

    @Test
    public void negativeWithdrawAmountShouldThrowException() {
        AccountStatement account = new AccountStatement();
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));

        // The balance and the operation history should remain unchanged after a failed withdrawal
        assertEquals(0, account.getAccountBalance());
        assertEquals(0, account.getOperationHistory().size());
    }

}

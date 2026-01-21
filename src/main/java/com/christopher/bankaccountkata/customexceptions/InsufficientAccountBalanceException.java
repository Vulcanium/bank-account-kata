package com.christopher.bankaccountkata.customexceptions;

public class InsufficientAccountBalanceException extends RuntimeException {

    public InsufficientAccountBalanceException(String message) {
        super(message);
    }
}

package org.christopher.bankaccountkata;

public class Main {

    public static void main(String[] args) {
        AccountStatement accountStatement = new AccountStatement();
        AccountStatementPrinter printer = new AccountStatementPrinter();

        accountStatement.deposit(100.0);
        accountStatement.withdraw(25.5);
        accountStatement.deposit(50.0);
        accountStatement.withdraw(10.0);

        System.out.println(printer.print(accountStatement.getOperationHistory()));
    }
}

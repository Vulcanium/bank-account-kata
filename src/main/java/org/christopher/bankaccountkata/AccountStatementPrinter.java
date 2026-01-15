package org.christopher.bankaccountkata;

import org.christopher.bankaccountkata.Operation.OperationType;

import java.util.List;

public class AccountStatementPrinter {

    public String print(List<Operation> operations) {
        StringBuilder sb = new StringBuilder();

        // Adding the header
        sb.append("Date | Amount | Balance\n");

        // Adding the operation history
        for (Operation operation : operations) {
            String negativeSign = "";

            if (operation.operationType().equals(OperationType.WITHDRAWAL)) {
                negativeSign = "-";
            }

            sb.append(operation.date().toString())
                    .append(" | ")
                    .append(negativeSign)
                    .append(operation.amount())
                    .append(" | ")
                    .append(operation.balance())
                    .append("\n");
        }

        return sb.toString();
    }
}

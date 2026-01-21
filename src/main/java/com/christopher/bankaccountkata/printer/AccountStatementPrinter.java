package com.christopher.bankaccountkata.printer;

import com.christopher.bankaccountkata.domain.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountStatementPrinter {

    public String print(List<Operation> operationHistory) {
        StringBuilder sb = new StringBuilder();

        // Adding the header
        sb.append("Date | Amount | Balance\n");

        // Adding the operation history
        for (Operation operation : operationHistory) {

            sb.append(operation.getDate().toString())
                    .append(" | ")
                    .append(operation.getAmount())
                    .append(" | ")
                    .append(operation.getBalance())
                    .append("\n");
        }

        return sb.toString();
    }
}

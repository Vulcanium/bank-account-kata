package com.christopher.bankaccountkata.controller;

import com.christopher.bankaccountkata.domain.AccountStatement;
import com.christopher.bankaccountkata.domain.Operation;
import com.christopher.bankaccountkata.printer.AccountStatementPrinter;
import com.christopher.bankaccountkata.service.AccountStatementService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountStatementController.class)
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class AccountStatementControllerTest {

    private final MockMvc mockMvc;

    @MockitoBean
    private AccountStatementService service;

    @MockitoBean
    private AccountStatementPrinter printer;

    @Test
    void createAccountEndpointShouldReturnAccount() throws Exception {
        AccountStatement account = new AccountStatement();
        account.deposit(100);
        account.deposit(50);

        when(service.createAccount()).thenReturn(account);

        mockMvc.perform(post("/accounts"))
                .andExpect(status().isOk());
    }

    @Test
    void depositEndpointShouldCallService() throws Exception {
        mockMvc.perform(post("/accounts/deposit/1")
                        .param("amount", "100"))
                .andExpect(status().isOk());

        verify(service).deposit(1L, 100);
    }

    @Test
    void withdrawEndpointShouldCallService() throws Exception {
        mockMvc.perform(post("/accounts/withdraw/1")
                        .param("amount", "50"))
                .andExpect(status().isOk());

        verify(service).withdraw(1L, 50);
    }

    @Test
    void statementEndpointShouldReturnPrintedStatement() throws Exception {
        List<Operation> operationHistory = new ArrayList<>();
        operationHistory.add(new Operation(new AccountStatement(), LocalDate.now(), 100.0, 100.0));

        when(service.getOperationHistory(1L)).thenReturn(operationHistory);
        when(printer.print(operationHistory)).thenReturn("Printed Statement");

        mockMvc.perform(get("/accounts/statement/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Printed Statement"));
    }
}

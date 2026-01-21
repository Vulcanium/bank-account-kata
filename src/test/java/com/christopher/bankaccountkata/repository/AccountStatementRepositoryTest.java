package com.christopher.bankaccountkata.repository;

import com.christopher.bankaccountkata.domain.AccountStatement;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AllArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class AccountStatementRepositoryTest {

    private AccountStatementRepository repository;

    @Test
    void saveAndLoadAccountStatement() {
        AccountStatement account = new AccountStatement();
        repository.save(account);

        assertNotNull(account.getId());
    }
}

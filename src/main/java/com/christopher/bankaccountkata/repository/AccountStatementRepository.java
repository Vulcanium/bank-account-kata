package com.christopher.bankaccountkata.repository;

import com.christopher.bankaccountkata.domain.AccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatementRepository extends JpaRepository<AccountStatement, Long> {
}

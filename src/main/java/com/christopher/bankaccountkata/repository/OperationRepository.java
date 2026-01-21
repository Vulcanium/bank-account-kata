package com.christopher.bankaccountkata.repository;

import com.christopher.bankaccountkata.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccountStatementId(Long accountStatementId);
}

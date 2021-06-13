package com.supportportal.repository;

import com.supportportal.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Transaction findTransactionByTransactionNumberAndAccountNumber(String transactionNumber,String accountNumber);
    List<Transaction> findTransactionByAccountNumber(String accountNumber);
    List<Transaction> findTransactionByUserId(String userId);

}

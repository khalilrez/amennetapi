package com.supportportal.service;

import com.supportportal.domain.Account;
import com.supportportal.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface TransactionService {
    Transaction addNewTransaction(String accountNumber,String userId, String amount, String desc, Date date, int i);
    Transaction findTransactionByTransactionNumberAndAccountNumber(String transactionNumber,String accountNumber);
    List<Transaction> findTransactionByAccountNumber(String accountNumber);
    List<Transaction> findTransactionByUserId(String userId);

}

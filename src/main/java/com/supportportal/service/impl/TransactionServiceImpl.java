package com.supportportal.service.impl;


import com.supportportal.domain.Account;
import com.supportportal.domain.Transaction;
import com.supportportal.repository.TransactionRepository;
import com.supportportal.service.TransactionService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction addNewTransaction(String accountNumber,String userId, String amount, String desc, Date date, int i) {
        String transactionNumber = RandomStringUtils.randomNumeric(10);

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(transactionNumber);
        transaction.setAccountNumber(accountNumber);
        transaction.setUserId(userId);
        double _amount = Double.parseDouble(amount);
        transaction.setAmount(_amount);
        transaction.setDescription(desc);
        transaction.setType(i);
        transaction.setTransactionDate(date);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction findTransactionByTransactionNumberAndAccountNumber(String transactionNumber, String accountNumber) {
        return transactionRepository.findTransactionByTransactionNumberAndAccountNumber(transactionNumber,accountNumber);
    }


    @Override
    public List<Transaction> findTransactionByAccountNumber(String accountNumber) {
        return transactionRepository.findTransactionByAccountNumber(accountNumber);
    }

    @Override
    public List<Transaction> findTransactionByUserId(String userId) {
        return transactionRepository.findTransactionByUserId(userId);
    }
}

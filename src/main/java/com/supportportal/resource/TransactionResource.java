package com.supportportal.resource;

import com.supportportal.domain.Account;
import com.supportportal.domain.Transaction;
import com.supportportal.exception.domain.AccountNumberExistException;
import com.supportportal.exception.domain.AccountNumberNotFoundException;
import com.supportportal.exception.domain.IbanExistException;
import com.supportportal.exception.domain.RibExistException;
import com.supportportal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/transaction"})
public class TransactionResource {

    private TransactionService transactionService;

    @Autowired
    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/find")
    public ResponseEntity<Transaction> getTransaction(@RequestParam("transactionNumber") String transactionNumber,
                                                       @RequestParam("accountNumber") String accountNumber
    ) {
        Transaction transaction = transactionService.findTransactionByTransactionNumberAndAccountNumber(transactionNumber,accountNumber);
        return new ResponseEntity<>(transaction, OK);
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionByAccountNumber(@PathVariable("accountNumber") String accountNumber
    ) {
        List<Transaction> transaction = transactionService.findTransactionByAccountNumber(accountNumber);
        return new ResponseEntity<>(transaction, OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionByUserId(@PathVariable("userId") String userId
    ) {
        List<Transaction> transaction = transactionService.findTransactionByUserId(userId);
        return new ResponseEntity<>(transaction, OK);
    }

}

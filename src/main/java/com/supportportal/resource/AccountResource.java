package com.supportportal.resource;


import com.supportportal.domain.Account;
import com.supportportal.domain.HttpResponse;
import com.supportportal.exception.domain.*;
import com.supportportal.service.AccountService;
import com.supportportal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/account"})
public class AccountResource {

    public static final String ACCOUNT_DELETED_SUCCESSFULLY = "Account Deleted Successfully";
    public static final String TRANSFER_COMPLETED = "TRANSFER COMPLETED";

    private AccountService accountService;
    private TransactionService transactionService;

    @Autowired
    public AccountResource(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }


    @PostMapping("/add")
    public ResponseEntity<Account> addNewAccount(@RequestParam("accountNumber") String accountNumber,
                                                 @RequestParam("rib") String rib,
                                                 @RequestParam("iban") String iban,
                                                 @RequestParam("lib") String lib,
                                                 @RequestParam("balance") double balance,
                                                 @RequestParam("ownerId") String ownerId
                                                    ) throws AccountNumberNotFoundException, RibExistException, AccountNumberExistException, IbanExistException {
        Account newAccount = accountService.addNewAccount(accountNumber,rib,iban,lib,balance,ownerId);
        return new ResponseEntity<>(newAccount, OK);

    }

    @PostMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestParam("currentAccountNumber") String currentAccountNumber,
                                                 @RequestParam("accountNumber") String accountNumber,
                                                 @RequestParam("rib") String rib,
                                                 @RequestParam("iban") String iban,
                                                 @RequestParam("lib") String lib,
                                                 @RequestParam("balance") double balance) throws AccountNumberNotFoundException, RibExistException, AccountNumberExistException, IbanExistException {
        Account updatedAccount = accountService.updateAccount(currentAccountNumber,accountNumber,rib,iban,lib,balance);
        return new ResponseEntity<>(updatedAccount, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAccounts();
        return new ResponseEntity<>(accounts,OK);
    }

    @GetMapping("/id/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable("accountNumber") String accountNumber){
        Account account = accountService.getAccountByNumber(accountNumber);
        return new ResponseEntity<>(account,OK);
    }

    @GetMapping("/user/{ownerId}")
    public ResponseEntity<List<Account>> getAccountByOwner(@PathVariable("ownerId") String ownerId){
        List<Account> account = accountService.getAccountByOwner(ownerId);
        return new ResponseEntity<>(account,OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<HttpResponse> deleteAccount(@PathVariable("accountNumber") String accountNumber){
        accountService.deleteAccount(accountNumber);
        return response(OK, ACCOUNT_DELETED_SUCCESSFULLY);
    }

    @PostMapping("/transfer")
    public ResponseEntity<HttpResponse> transfer(@RequestParam("from") String from,
                                                 @RequestParam("to") String to,
                                                 @RequestParam("amount") String amount,
                                                 @RequestParam("desc") String desc
                                                ) throws AccountNumberNotFoundException, FundsTooLowException {
        Date date= new Date();
        Account fromAccount = accountService.getAccountByNumber(from);
        Account toAccount = accountService.getAccountByNumber(to);
            accountService.transferAmount(fromAccount,toAccount,amount);
            transactionService.addNewTransaction(from,fromAccount.getOwnerId(),amount,desc,date,0); // 0 pour debit
            transactionService.addNewTransaction(to,toAccount.getOwnerId(),amount,desc,date,1); // 1 pour credit
        return response(OK, TRANSFER_COMPLETED);
    }



    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }




}

package com.supportportal.service;

import com.supportportal.domain.Account;
import com.supportportal.exception.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {
    Account addNewAccount(String accountNumber, String rib, String iban, String lib, double balance, String ownerId) throws AccountNumberNotFoundException, RibExistException, AccountNumberExistException, IbanExistException;

    Account updateAccount(String currentAccountNumber, String accountNumber, String rib, String iban, String lib, double balance) throws AccountNumberNotFoundException, RibExistException, AccountNumberExistException, IbanExistException;

    List<Account> getAccounts();

    Account getAccountByNumber(String accountNumber);

    List<Account> getAccountByOwner(String ownerId);

    Account findAccountByRib(String rib);

    Account findAccountByIban(String iban);

    void deleteAccount(String accountNumber);

    void transferAmount(Account fromAccount, Account toAccount, String amount) throws AccountNumberNotFoundException, FundsTooLowException;


}

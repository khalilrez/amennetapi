package com.supportportal.service.impl;

import com.supportportal.domain.Account;
import com.supportportal.domain.User;
import com.supportportal.exception.domain.*;
import com.supportportal.repository.AccountRepository;
import com.supportportal.service.AccountService;
import com.supportportal.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.supportportal.constant.UserImplConstant.*;
import static org.apache.commons.lang3.StringUtils.*;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    public static final String ACCOUNT_NUMBER_ALREADY_EXISTS = "ACCOUNT NUMBER ALREADY EXISTS";
    public static final String ACCOUNT_WITH_THIS_RIB_ALREADY_EXISTS = "ACCOUNT WITH THIS RIB ALREADY EXISTS";
    public static final String ACCOUNT_WITH_THIS_IBAN_ALREADY_EXISTS = "ACCOUNT WITH THIS IBAN ALREADY EXISTS";
    public static final String NO_ACCOUNT_FOUND_BY_ACCOUNT_NUMBER = "NO ACCOUNT FOUND BY ACCOUNT NUMBER :";
    private AccountRepository accountRepository;
    private UserService userService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Override
    public Account addNewAccount(String accountNumber, String rib, String iban, String lib, double balance, String ownerId) throws AccountNumberNotFoundException, RibExistException, AccountNumberExistException, IbanExistException {
       validateNewAccount(EMPTY,accountNumber,rib,iban);
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setRib(rib);
        account.setIban(iban);
        account.setLib(lib);
        account.setBalance(balance);
        account.setOwnerId(ownerId);
        account.setUser(userService.findUserByUsername(ownerId));
        accountRepository.save(account);
        return account;

    }

    @Override
    public Account updateAccount(String currentAccountNumber, String newAccountNumber, String newRib, String newIban,
                                 String newLib, double newBalance) throws AccountNumberNotFoundException, RibExistException, AccountNumberExistException, IbanExistException {
        Account currentAccount =  validateNewAccount(currentAccountNumber,newAccountNumber,newRib,newIban);
        currentAccount.setAccountNumber(newAccountNumber);
        currentAccount.setRib(newRib);
        currentAccount.setIban(newIban);
        currentAccount.setLib(newLib);
        currentAccount.setBalance(newBalance);
        accountRepository.save(currentAccount);
        return currentAccount;
    }


    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> getAccountByOwner(String ownerId) {
        return accountRepository.findAccountByOwnerId(ownerId);
    }

    @Override
    public Account findAccountByRib(String rib) {
        return accountRepository.findAccountByRib(rib);
    }

    @Override
    public Account findAccountByIban(String iban) {
        return accountRepository.findAccountByIban(iban);
    }

    @Override
    public void deleteAccount(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        accountRepository.deleteById(account.getIdAccount());
    }

    @Override
    public void transferAmount(Account fromAccount, Account toAccount, String amount) throws AccountNumberNotFoundException, FundsTooLowException {
        validateTransfer(fromAccount,toAccount,amount);
        double _amount = Double.parseDouble(amount);
        double currentBalanceFrom = fromAccount.getBalance();
        double currentBalanceTo = toAccount.getBalance();
        fromAccount.setBalance(currentBalanceFrom - _amount);
        toAccount.setBalance(currentBalanceTo + _amount);
    }




    //PRIVATE METHODS

    private void validateTransfer(Account fromAccount, Account toAccount, String amount) throws FundsTooLowException, AccountNumberNotFoundException {
        double _amount = Double.parseDouble(amount);
        if (fromAccount != null && toAccount != null) {
            double currentBalanceFrom = fromAccount.getBalance();
            if (currentBalanceFrom < _amount) {
                throw new FundsTooLowException("Funds too Low");
            }
        } else {
            if (fromAccount == null){ throw new AccountNumberNotFoundException("No Sending Account found");}

            if (toAccount == null){ throw new AccountNumberNotFoundException("No Receiving Account found");}
            }

        }

    private Account validateNewAccount(String currentAccountNumber, String newAccountNumber, String newRib, String newIban) throws AccountNumberExistException, RibExistException, IbanExistException, AccountNumberNotFoundException {
        Account accountByNewAccountNumber = getAccountByNumber(newAccountNumber);
        Account accountByNewRib = findAccountByRib(newRib);
        Account accountByNewIban = findAccountByIban(newIban);
        if(StringUtils.isNotBlank(currentAccountNumber)) {
            Account currentAccount = getAccountByNumber(currentAccountNumber);
            if(currentAccount == null) {
                throw new AccountNumberNotFoundException(NO_ACCOUNT_FOUND_BY_ACCOUNT_NUMBER + currentAccountNumber);
            }
            if(accountByNewAccountNumber != null && !currentAccount.getIdAccount().equals(accountByNewAccountNumber.getIdAccount())) {
                throw new AccountNumberExistException(ACCOUNT_NUMBER_ALREADY_EXISTS);
            }
            if(accountByNewRib != null && !currentAccount.getIdAccount().equals(accountByNewRib.getIdAccount())) {
                throw new RibExistException(ACCOUNT_WITH_THIS_RIB_ALREADY_EXISTS);
            }
            if(accountByNewIban != null && !currentAccount.getIdAccount().equals(accountByNewIban.getIdAccount())) {
                throw new IbanExistException(ACCOUNT_WITH_THIS_IBAN_ALREADY_EXISTS);
            }
            return currentAccount;
        } else {
            if(accountByNewAccountNumber != null) {
                throw new AccountNumberExistException(ACCOUNT_NUMBER_ALREADY_EXISTS);
            }
            if(accountByNewRib != null) {
                throw new RibExistException(ACCOUNT_WITH_THIS_RIB_ALREADY_EXISTS);
            }
            if(accountByNewIban != null) {
                throw new IbanExistException(ACCOUNT_WITH_THIS_IBAN_ALREADY_EXISTS);
            }
            return null;
        }
    }



}

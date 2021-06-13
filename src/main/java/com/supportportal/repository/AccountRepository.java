package com.supportportal.repository;

import com.supportportal.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAccountByOwnerId(String ownerId);
    Account findAccountByAccountNumber(String accountNumber);
    Account findAccountByRib(String rib);
    Account findAccountByIban(String iban);

}

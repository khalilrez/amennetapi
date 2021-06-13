package com.supportportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Table(name="Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idAccount;
    private String accountNumber;
    private String rib;
    private String iban;
    private String lib;
    private double balance;
    public static final String BIC_CONSTANT = "AMEN CFCTTNTT";
    private String bic;
    @Column(unique = true)
    private String ownerId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pid", nullable = false)
    private User user;


    public Account() {
        this.bic=BIC_CONSTANT;
    }



    public Account(Long idAccount, String accountNumber, String rib, String iban, String lib, double balance, String ownerId, User user) {
        this.idAccount = idAccount;
        this.accountNumber = accountNumber;
        this.rib = rib;
        this.iban = iban;
        this.lib = lib;
        this.bic=BIC_CONSTANT;
        this.balance = balance;
        this.ownerId = ownerId;
        this.user = user;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBic() {
        return bic;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.supportportal.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long transactionId;
    private String transactionNumber;
    private String userId;
    private String accountNumber;
    private double amount;
    private String description;
    private Date TransactionDate;
    private int type;

    public Transaction() {
    }



    public Transaction(Long transactionId, String transactionNumber, String userId, String accountNumber, double amount, String description, Date transactionDate, int type) {
        this.transactionId = transactionId;
        this.transactionNumber = transactionNumber;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
        TransactionDate = transactionDate;
        this.type = type;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package com.example.library.models.dto;

import com.example.library.models.Patron;
import com.example.library.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatronDTO {
    private int id;
    private String name;
    private String username;
    private String emailId;
    private String phoneNo;
    private List<Integer> transactions = new ArrayList<>();

    public PatronDTO(Patron patron) {
        this.id = patron.getId();
        this.name = patron.getName();
        this.username = patron.getUsername();
        this.emailId = patron.getEmailId();
        this.phoneNo = patron.getPhoneNo();
        for(Transaction transaction:patron.getTransactionHistory())
            this.transactions.add(transaction.getId());
    }


    public List<Integer> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Integer> transactions) {
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}

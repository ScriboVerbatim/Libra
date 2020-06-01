package com.example.library.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;
    private String username;
    private String password;
    private String emailId;
    private String phoneNo;

    @OneToMany(mappedBy = "patron")
    private List<Transaction> transactionHistory =new ArrayList<>();


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

    public Patron() { }

    public Patron(int id, String name, String username, String password, String emailId, String phoneNo) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}

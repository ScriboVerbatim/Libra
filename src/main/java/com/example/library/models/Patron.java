package com.example.library.models;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private boolean removed;
    private Timestamp dateOfJoin;
    private Timestamp dateOfExit;

    @OneToMany(mappedBy = "patron")
    private List<Transaction> transactionHistory =new ArrayList<>();

    public Patron() { }

    public Patron(int id, String name, String username, String password, String emailId, String phoneNo) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        //this.dateOfJoin = current date
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
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

    public Timestamp getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Timestamp dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public Timestamp getDateOfExit() {
        return dateOfExit;
    }

    public void setDateOfExit(Timestamp dateOfExit) {
        this.dateOfExit = dateOfExit;
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

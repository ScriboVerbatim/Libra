package com.example.library.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean borrowed;

    private String state;

    private boolean removed;

    private Timestamp dateOfAddition;

    private Timestamp dateOfRemoval;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookType bookType;

    @OneToMany(mappedBy = "bookUnit")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Transaction> transactionHistory = new ArrayList<>();

    public BookUnit() {
    }

    public BookUnit(int id, boolean borrowed, String state, BookType bookType) {
        this.id = id;
        this.borrowed = borrowed;
        this.state = state;
        this.bookType = bookType;
        //set dateOfAddition
    }

    public Timestamp getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(Timestamp dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public Timestamp getDateOfRemoval() {
        return dateOfRemoval;
    }

    public void setDateOfRemoval(Timestamp dateOfRemoval) {
        this.dateOfRemoval = dateOfRemoval;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}

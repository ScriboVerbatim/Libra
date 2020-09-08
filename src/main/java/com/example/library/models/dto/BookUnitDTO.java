package com.example.library.models.dto;

import com.example.library.models.BookType;
import com.example.library.models.BookUnit;
import com.example.library.models.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookUnitDTO {
    private int id;

    private boolean borrowed;

    private String state;

    private boolean removed;

    private Timestamp dateOfAddition;

    private Timestamp dateOfRemoval;

    private BookTypeDTO bookType;

    private List<TransactionDTO> transactionHistory = new ArrayList<>();

    public BookUnitDTO(BookUnit bookUnit) {
        this.id = bookUnit.getId();
        this.borrowed = bookUnit.isBorrowed();
        this.state = bookUnit.getState();
        this.removed = bookUnit.isRemoved();
        this.dateOfAddition = bookUnit.getDateOfAddition();
        this.dateOfRemoval = bookUnit.getDateOfRemoval();
        this.bookType = new BookTypeDTO(bookUnit.getBookType());
        for(Transaction transaction:bookUnit.getTransactionHistory())
            this.transactionHistory.add(new TransactionDTO(transaction));
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

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
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

    public BookTypeDTO getBookType() {
        return bookType;
    }

    public void setBookType(BookTypeDTO bookType) {
        this.bookType = bookType;
    }

    public List<TransactionDTO> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<TransactionDTO> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}

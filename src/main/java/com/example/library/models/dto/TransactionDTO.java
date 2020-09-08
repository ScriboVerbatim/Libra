package com.example.library.models.dto;

import com.example.library.models.Transaction;


import java.sql.Timestamp;

public class TransactionDTO {

    private Timestamp issueDate;

    private Timestamp returnDate;

    private float fine;

    private Boolean goodReturnCondition;

    private String remarks;

    private int patronId;

    private int bookId;

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.issueDate = transaction.getIssueDate();
        this.returnDate = transaction.getReturnDate();
        this.fine = transaction.getFine();
        this.goodReturnCondition = transaction.isGoodReturnCondition();
        this.remarks = transaction.getRemarks();
        this.patronId = transaction.getPatron().getId();
        this.bookId = transaction.getBookUnit().getId();
    }


    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Boolean isGoodReturnCondition() {
        return goodReturnCondition;
    }

    public void setGoodReturnCondition(Boolean goodReturnCondition) {
        this.goodReturnCondition = goodReturnCondition;
    }
}

package com.example.library.models.dto;

import com.example.library.models.Transaction;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

public class TransactionDTO {

    @NonNull
    private Timestamp issueDate;

    private Timestamp returnDate;

    private float fine;

    private boolean goodReturnCondition;

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

    @NonNull
    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(@NonNull Timestamp issueDate) {
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

    public boolean isGoodReturnCondition() {
        return goodReturnCondition;
    }

    public void setGoodReturnCondition(boolean goodReturnCondition) {
        this.goodReturnCondition = goodReturnCondition;
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
}

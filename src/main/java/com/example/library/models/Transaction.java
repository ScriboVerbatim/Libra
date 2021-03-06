package com.example.library.models;

import com.example.library.models.dto.IssueDTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Timestamp issueDate;

    private Timestamp returnDate;

    private float fine;

    private Boolean goodReturnCondition;

    private String remarks;

    @ManyToOne
    //@JoinColumn(name="patronId")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name="bookId")
    private BookUnit bookUnit;

    public Transaction() {
    }

    public Transaction(int id, Timestamp issueDate, Timestamp returnDate, float fine, Boolean goodReturnCondition, String remarks, Patron patron, BookUnit bookUnit) {
        this.id = id;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.goodReturnCondition = goodReturnCondition;
        this.remarks = remarks;
        this.patron = patron;
        this.bookUnit = bookUnit;
    }

    public Boolean isGoodReturnCondition() {
        return goodReturnCondition;
    }

    public void setGoodReturnCondition(Boolean goodReturnCondition) {
        this.goodReturnCondition = goodReturnCondition;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public BookUnit getBookUnit() {
        return bookUnit;
    }

    public void setBookUnit(BookUnit bookUnit) {
        this.bookUnit = bookUnit;
    }
}

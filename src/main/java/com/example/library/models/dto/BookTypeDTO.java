package com.example.library.models.dto;

import com.example.library.models.BookType;
import com.example.library.models.BookUnit;
import com.example.library.models.Tag;


import java.util.ArrayList;
import java.util.List;


public class BookTypeDTO {
    private String isbn;
    private String title;
    private String author;
    private String subject;
    private List<String> tags = new ArrayList<>();
    private int totalCopies;
    private int availableCopies;
    private List<Integer> bookUnits = new ArrayList<>();

    public BookTypeDTO() {
    }

    public BookTypeDTO(BookType bookType) {
        this.isbn=bookType.getIsbn();
        this.title=bookType.getTitle();
        this.author=bookType.getAuthor();
        this.subject=bookType.getSubject();

        this.totalCopies = 0;
        this.availableCopies = 0;
        for(BookUnit bookUnit:bookType.getBookUnits()) {
            if (!bookUnit.isRemoved()) {
                this.totalCopies++;
                this.bookUnits.add(bookUnit.getId());
                if (!bookUnit.isBorrowed())
                    this.availableCopies++;
            }
        }

        for(Tag tag:bookType.getTags())
            this.tags.add(tag.getTagName());
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public List<Integer> getBookUnits() {
        return bookUnits;
    }

    public void setBookUnits(List<Integer> bookUnits) {
        this.bookUnits = bookUnits;
    }
}

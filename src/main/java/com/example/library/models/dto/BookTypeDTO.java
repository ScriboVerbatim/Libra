package com.example.library.models.dto;

import com.example.library.models.BookType;
import com.example.library.models.Tag;


import java.util.ArrayList;


public class BookTypeDTO {
    private String isbn;
    private String title;
    private String author;
    private String subject;
    private ArrayList<String> tags;
    private int copies;

    public BookTypeDTO() {
    }

    public BookTypeDTO(BookType bookType) {
        this.isbn=bookType.getIsbn();
        this.title=bookType.getTitle();
        this.author=bookType.getAuthor();
        this.subject=bookType.getSubject();
        this.copies=bookType.getBookUnits().size();
        for(Tag tag:bookType.getTags())
            this.tags.add(tag.getTagName());
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

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}

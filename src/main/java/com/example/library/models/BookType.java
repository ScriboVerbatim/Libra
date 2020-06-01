package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookType{
    @Id
    private String isbn;

    private String title;

    private String author;

    private String subject;

    @OneToMany(mappedBy = "bookType")//,cascade={CascadeType.PERSIST})
    private List<BookUnit> bookUnits=new ArrayList<>();

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(mappedBy = "bookTypes")
    private List<Tag> tags=new ArrayList<>();

    public BookType() { }

    public BookType(String isbn, String title, String author, String subject) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.subject = subject;

    }

    public List<BookUnit> getBookUnits() {
        return bookUnits;
    }

    public void setBookUnits(List<BookUnit> bookUnits) {
        this.bookUnits = bookUnits;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

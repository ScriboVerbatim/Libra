package com.example.library.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    private String tagName;

    @ManyToMany
    private List<BookType> bookTypes=new ArrayList<>();

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }


    public List<BookType> getBookTypes() {
        return bookTypes;
    }

    public void setBookTypes(List<BookType> bookTypes) {
        this.bookTypes = bookTypes;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


}

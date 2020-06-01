package com.example.library.models.dto;

import com.example.library.models.Patron;

public class PatronDTO {
    private int id;
    private String name;
    private String username;
    private String emailId;
    private String phoneNo;

    public PatronDTO(Patron patron) {
        this.id = patron.getId();
        this.name = patron.getName();
        this.username = patron.getUsername();
        this.emailId = patron.getEmailId();
        this.phoneNo = patron.getPhoneNo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}

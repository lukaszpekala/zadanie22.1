package com.example.zadanie22_1;

import org.springframework.stereotype.Repository;

@Repository
public class Message {

    private String email;
    private String name;
    private String text;

    public Message(String email, String name, String text) {
        this.email = email;
        this.name = name;
        this.text = text;
    }

    public Message() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

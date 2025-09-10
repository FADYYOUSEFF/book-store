package com.example.book_store.enums;

public enum UserRole {
    MEMBER("MEMBER"),
    STAFF("STAFF");

    private String name;

    UserRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

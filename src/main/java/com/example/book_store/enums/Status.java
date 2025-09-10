package com.example.book_store.enums;

public enum Status {
    BORROWED("BORROWED"),
    AVAILABLE("AVAILABLE");

    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

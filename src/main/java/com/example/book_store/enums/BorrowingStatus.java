package com.example.book_store.enums;

public enum BorrowingStatus {
    BORROWED("BORROWED"),
    RETURNED("RETURNED");

    private String name;

    BorrowingStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

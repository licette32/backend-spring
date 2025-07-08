package com.techlab.backend;


public class Error {
    private int Id;
    private String message;

    public int getId() {
        return Id;
    }
    public String getMessage() {
        return message;
    }
    public void setId(int id) {
        Id = id;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Error(int id, String message) {
        Id = id;
        this.message = message;
    }
}

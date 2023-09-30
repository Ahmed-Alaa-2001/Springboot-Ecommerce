package com.example.SpringbootEcommerce.exceptions;

public class UserExists extends Exception{
    public UserExists(String message) {
        super(message);
    }
}

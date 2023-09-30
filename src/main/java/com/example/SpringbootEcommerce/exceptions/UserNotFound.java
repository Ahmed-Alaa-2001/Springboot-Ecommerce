package com.example.SpringbootEcommerce.exceptions;

public class UserNotFound extends Exception{
    public UserNotFound(String message) {
        super(message);
    }
}

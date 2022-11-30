package com.example.exception;

public class AccountAlreadyExistException extends Exception {
    public AccountAlreadyExistException(String s){
        super(s);
    }

}

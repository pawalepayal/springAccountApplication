package com.example.exception;

public class EmptyListException extends  RuntimeException {

    public EmptyListException(String s) {
        super(s);
    }
}
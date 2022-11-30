package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class InvalidEntryException extends RuntimeException {
    public InvalidEntryException(String s){

        super(s);
    }
}

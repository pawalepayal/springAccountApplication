package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends  Exception{
   private String msg;
   public DataNotFoundException(){

   }

    public DataNotFoundException(String message){
        super(message);
        this.msg=message;

    }
}

package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DataNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse dataNotFoundException(DataNotFoundException e){
        return  new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
    }

    @ExceptionHandler(value= AccountAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody   ErrorResponse accountAlreadyExistException(AccountAlreadyExistException e){
        return  new ErrorResponse(HttpStatus.CONFLICT.value(),e.getMessage());
    }

    @ExceptionHandler(value= EmptyListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody   ErrorResponse emptyListException(EmptyListException e){
        return  new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }

    @ExceptionHandler(value= InvalidEntryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody   ErrorResponse invalidEntryException(InvalidEntryException e){
        return  new ErrorResponse(HttpStatus.CONFLICT.value(),e.getMessage());
    }

}

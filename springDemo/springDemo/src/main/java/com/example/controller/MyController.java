package com.example.controller;

import com.example.entities.Account;
import com.example.exception.DataNotFoundException;
import com.example.exception.InvalidEntryException;
import com.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RestController
public class MyController {

    @Autowired
    private Service service;

    @RequestMapping("/home")
    public String home() {

        return "welcome..";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account/get")
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }


    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/account/get/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable long accountNumber) throws InvalidEntryException {

        return service.getAccountByAccountNumber(accountNumber);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/account/add")
    public Account addAccount(@RequestBody Account account) throws InvalidEntryException {
        return service.addAccount(account);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/account/update/{accountNumber}")
    public Account updateAccount(@PathVariable long accountNumber, @RequestBody Account account) {
        return service.updateAccount(accountNumber, account);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/account/delete/{accountNumber}")
    public String deleteAccount(@PathVariable long accountNumber) throws DataNotFoundException {
        return service.deleteAccount(accountNumber);
    }

}

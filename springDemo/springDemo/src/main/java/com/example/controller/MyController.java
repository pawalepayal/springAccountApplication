package com.example.controller;

import com.example.entities.Account;
import com.example.exception.DataNotFoundException;
import com.example.exception.InvalidEntryException;
import com.example.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RestController
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private Service service;

    @RequestMapping("/home")
    public String home() {
        logger.info("Library spring project Started");
        return "welcome..";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account/get")
    public List<Account> getAllAccounts() {
        logger.info("list of accounts");
        return service.getAllAccounts();
    }


    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/account/get/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable long accountNumber) throws InvalidEntryException {
        logger.info("Account for given id ");
        return service.getAccountByAccountNumber(accountNumber);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/account/add")
    public String addAccount(@RequestBody Account account) throws InvalidEntryException {
        logger.info("Account added");
        return service.addAccount(account);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/account/update/{accountNumber}")
    public String updateAccount(@PathVariable long accountNumber, @RequestBody Account account) {
        logger.info("Account updated");
        return service.updateAccount(accountNumber, account);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/account/delete/{accountNumber}")
    public String deleteAccount(@PathVariable long accountNumber) throws DataNotFoundException {
        logger.info("Account deleted");
        return service.deleteAccount(accountNumber);
    }

}

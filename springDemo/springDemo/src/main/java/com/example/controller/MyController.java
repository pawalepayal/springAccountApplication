package com.example.controller;

import com.example.entities.Account;
import com.example.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);


    @Autowired
    private Service service;

    @RequestMapping("/home")
    public String home() {
        return "welcome..";
    }

    @GetMapping("/account")
    public List<Account> getAccounts() {
        logger.info("list of accounts");
        return this.service.getAccounts();
    }


    @GetMapping("/account/get/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        logger.info("Account for given id ");
        return this.service.getAccount(Integer.parseInt(accountId));
    }

    @PostMapping("/account/add") //to add
    public List<Account> addAccount(@RequestBody Account account) {
        logger.info("Account added");

        return this.service.addAccount(account);
    }

    @PutMapping("/account/update/{accountNumber}")//to update
    public Account updateAccount(@PathVariable long accountNumber, @RequestBody Account account) {
        logger.info("Account updated");
        return this.service.updateAccount(accountNumber, account);
    }

    @DeleteMapping("/account/delete/{accountNumber}")
    public  String deleteAccount(@PathVariable long accountNumber) {
        logger.info("Account deleted");
        return this.service.deleteAccount(accountNumber);
    }

}

package com.example.controller;

import com.example.entities.Account;
import com.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private Service service;

    @RequestMapping("/home")
    public String home() {
        return "welcome..";
    }

    @GetMapping("/account")
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/account/get/{accountId}")
    public Account getAccountByAccoundNumber(@PathVariable long accoundNumber) {
        return service.getAccountByAccountNumber(accoundNumber);
    }

    @PostMapping("/account/add")
    public List<Account> addAccount(@RequestBody Account account) {
        return service.addAccount(account);
    }

    @PutMapping("/account/update/{accountNumber}")
    public Account updateAccount(@PathVariable long accountNumber, @RequestBody Account account) {
        return service.updateAccount(accountNumber, account);
    }

    @DeleteMapping("/account/delete/{accountNumber}")
    public  String deleteAccount(@PathVariable long accountNumber) {
        return service.deleteAccount(accountNumber);
    }

}

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
    public List<Account> getAccounts() {
        return this.service.getAccounts();
    }

    @GetMapping("/account/get/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        return this.service.getAccount(Integer.parseInt(accountId));
    }

    @PostMapping("/account/add") //to add
    public List<Account> addAccount(@RequestBody Account account) {

        return this.service.addAccount(account);
    }

    @PutMapping("/account/update/{accountNumber}")//to update
    public Account updateAccount(@PathVariable long accountNumber, @RequestBody Account account) {
        return this.service.updateAccount(accountNumber, account);
    }

    @DeleteMapping("/account/delete/{accountNumber}")
    public  String deleteAccount(@PathVariable long accountNumber) {
        return this.service.deleteAccount(accountNumber);
    }

}

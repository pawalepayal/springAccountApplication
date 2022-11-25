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

    @GetMapping("/account/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        return this.service.getAccount(Integer.parseInt(accountId));
    }

    @PostMapping("/account") //to add
    public Account addAccount(@RequestBody Account account) {

        return this.service.addAccount(account);
    }

    @PutMapping("/account/{accountId}")//to update
    public Account updateAccount(@PathVariable int accountId, @RequestBody Account account) {
        return this.service.updateAccount(accountId, account);
    }

    @DeleteMapping("/account/{accountId}")
    public Account deleteAccount(@PathVariable String accountId) {
        return this.service.deleteAccount(Integer.parseInt(accountId));
    }

}

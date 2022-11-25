package com.example.controller;

import com.example.entities.Account;
import com.example.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private ServiceImpl serviceImpl;

    @RequestMapping("/home")
    public String home() {
        return "welcome..";
    }

    @GetMapping("/account")
    public List<Account> getAccounts() {
        return this.serviceImpl.getAccounts();
    }

    @GetMapping("/account/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        return this.serviceImpl.getAccount(Integer.parseInt(accountId));
    }

    @PostMapping("/account") //to add
    public Account addAccount(@RequestBody Account account) {

        return this.serviceImpl.addAccount(account);
    }

    @PutMapping("/account/{accountId}")//to update
    public Account updateAccount(@PathVariable int accountId, @RequestBody Account account) {
        return this.serviceImpl.updateAccount(accountId, account);
    }

    @DeleteMapping("/account/{accountId}")
    public Account deleteAccount(@PathVariable String accountId) {
        return this.serviceImpl.deleteAccount(Integer.parseInt(accountId));
    }

}

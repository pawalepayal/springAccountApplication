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

    @RequestMapping("/Home")
    public String home() {
        return "welcome..";
    }

    @GetMapping("/Account")
    public List<Account> getAccounts() {
        return this.serviceImpl.getAccounts();
    }

    @GetMapping("/Account/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        return this.serviceImpl.getAccount(Integer.parseInt(accountId));
    }

    @PostMapping("/Account")
    public Account addAccount(@RequestBody Account account) {
        return this.serviceImpl.addAccount(account);
    }

    @PutMapping("/Account/{accountId}")
    public Account updateAccount(@PathVariable int accountId, @RequestBody Account account) {
        return this.serviceImpl.updateAccount(accountId, account);
    }

    @DeleteMapping("/Account/{accountId}")
    public Account deleteAccount(@PathVariable String accountId) {
        return this.serviceImpl.deleteAccount(Integer.parseInt(accountId));
    }

}

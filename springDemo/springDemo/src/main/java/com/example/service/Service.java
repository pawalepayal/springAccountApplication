package com.example.service;

import com.example.entities.Account;

import java.util.List;

public interface Service {
    public List<Account> getAccounts();
    Account getAccount(int accountId);
    public Account addAccount(Account account);
    Account deleteAccount(int accountId);
    Account updateAccount(int accountId,Account account);
}

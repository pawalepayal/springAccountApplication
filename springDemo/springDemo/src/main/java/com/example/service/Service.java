package com.example.service;

import com.example.entities.Account;

import java.util.List;

public interface Service {
    public List<Account> getAllAccounts();
    Account getAccountByAccountNumber(long accountNumber);
    public List<Account> addAccount(Account account);
    String deleteAccount(long accountNumber);
    Account updateAccount(long accountNumber,Account account);
}

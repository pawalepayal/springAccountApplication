package com.example.service;

import com.example.entities.Account;

import java.util.List;

public interface ServiceImpl {
    public List<Account> getAccounts();

    Account getAccount(long accountNumber);

    public List<Account> addAccount(Account account);
    String deleteAccount(long accountNumber);
    Account updateAccount(long accountNumber,Account account);
}

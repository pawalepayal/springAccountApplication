package com.example.service;

import com.example.entities.Account;
import com.example.exception.DataNotFoundException;
import com.example.exception.InvalidEntryException;

import java.util.List;

public interface Service {

    public List<Account> getAllAccounts();
    Account addAccount(Account account);

    Account getAccountByAccountNumber(long accountNumber) ;

    String deleteAccount(long accountNumber);

    Account updateAccount(long accountNumber, Account account);
}

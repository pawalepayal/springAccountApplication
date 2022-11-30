package com.example.service;

import com.example.entities.Account;
import com.example.exception.DataNotFoundException;
import com.example.exception.InvalidEntryException;

import java.util.List;

public interface Service {
    public List<Account> getAllAccounts();

    Account getAccountByAccountNumber(long accountNumber) throws InvalidEntryException;

    String addAccount(Account account) throws InvalidEntryException;

    String deleteAccount(long accountNumber) throws DataNotFoundException;

    String updateAccount(long accountNumber, Account account);
}

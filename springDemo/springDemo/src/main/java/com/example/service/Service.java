package com.example.service;

import com.example.entities.Account;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service implements ServiceImpl {
    List<Account> accountList = new ArrayList<>();

    public Service() {//int id, String accountHolderName, String branch, long mobileNumber
        accountList.add(new Account(12345, "Payal", "Pune",1234567891));
        accountList.add(new Account(56765, "Shivangi", "Delhi",914563578));
        accountList.add(new Account(12785, "Raj", "Mumbai",568799481));
        accountList.add(new Account(89755, "Sita", "Nashik",999998891));

    }

    @Override
    public List<Account> getAccounts() {
        return accountList;
    }

    @Override
    public Account getAccount(int accountId) {
        Account emp = null;
        for (Account account : accountList) {
            if (account.getId() == accountId) {
                emp = account;
                break;
            }
        }
        return emp;
    }

    @Override
    public Account addAccount(Account account) {
        accountList.add(account);
        return account;
    }

    @Override
    public Account deleteAccount(int accountId) {
        Account emp = null;
        for (Account account : accountList) {
            if (account.getId() == accountId) {
                emp = account;
                accountList.remove(account);
                break;
            }
        }
        return emp;
    }

    @Override
    public Account updateAccount(int accountId, Account account) {
        Account emp = null;
        Account result = getAccount(accountId);
        for (Account account1 : accountList) {
            if (account1.getId() == accountId) {
                account1.setaccountHolderName(account.getaccountHolderName());
                account1.setBranch(account.getBranch());
                account1.setMobileNumber(account.getMobileNumber());
                account1.setId(accountId);
                emp = account1;
                break;
            }
        }
        return emp;
    }


}

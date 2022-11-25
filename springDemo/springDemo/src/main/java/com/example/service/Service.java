package com.example.service;

import com.example.entities.Account;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service implements ServiceImpl {
    List<Account> accountList = new ArrayList<>();

    public Service() {//int id, String accountHolderName, String branch, long mobileNumber
        accountList.add(new Account(12345, "Payal", "Pune",1234567891,50000));
        accountList.add(new Account(56765, "Shivangi", "Delhi",914563578,40000));
        accountList.add(new Account(12785, "Raj", "Mumbai",568799481,34000));
        accountList.add(new Account(89755, "Sita", "Nashik",999998891,56000));
        accountList.add(new Account(8755, "abc", "xyz",998891,12000));
    }

    @Override
    public List<Account> getAccounts() {

        return accountList;
    }

    @Override
    public Account getAccount(int accountId) {
        Account emp = null;
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountId) {
                emp = account;
                break;
            }
        }
        return emp;
    }

    @Override
    public Account addAccount(Account account) {
       accountList.add(account);

        long mobileNumber= account.getMobileNumber();
        String phoneNumber = String.valueOf(mobileNumber);

        if (phoneNumber.length() == 10) {
            account.setMobileNumber(mobileNumber);
        } else {
            System.out.println("Invalid data");
            System.exit(0);
        }
        return account;
    }

    @Override
    public Account deleteAccount(int accountId) {
        Account emp = null;
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountId) {
                emp = account;
                accountList.remove(account);
                break;
            }
        }
        return emp;
    }


    public Account updateAccount(int accountId, Account account) {
        Account emp = null;
        for (Account account1 : accountList) {
            if (account1.getAccountNumber() == accountId) {
                account1.setaccountHolderName(account.getaccountHolderName());
                account1.setBranch(account.getBranch());
                account1.setMobileNumber(account.getMobileNumber());
                account1.setAccountBalance(account.getAccountBalance());
                account1.setAccountNumber(accountId);
                emp = account1;
                break;
            }
        }
        return emp;
    }


}

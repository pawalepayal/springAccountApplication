package com.example.service;

import com.example.entities.Account;
import com.example.exception.DataNotFoundException;
import com.example.exception.InvalidEntryException;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    List<Account> accountList = new ArrayList<>();

    public ServiceImpl() {
    }

    @Override
    public List<Account> getAllAccounts() {

        return accountList;
    }

    @Override
    public Account getAccountByAccountNumber(long accountNumber) {
        Account account = null;
        for (Account accountObj : accountList) {
            if (accountObj.getAccountNumber() == accountNumber) {
                account = accountObj;
                break;
            }
        }
        return account;
    }

    @Override
    public List<Account> addAccount(Account account) {

        long mobileNumber = account.getMobileNumber();
        String phoneNumber = String.valueOf(mobileNumber);

        long accountNumber = account.getAccountNumber();
        String accountId = String.valueOf(accountNumber);

        try {
            if (phoneNumber.length() != 10) {
                throw new InvalidEntryException("Phone number must be a 10 digit long");
            }
            if (accountId.length() != 14) {
                throw new InvalidEntryException("Accont number length must be a 14 digit long");
            }
            if (!(account.getaccountHolderName().length() > 4)) {
                throw new InvalidEntryException("Accont Holder name length should  be more than 4 letter");
            } else
                accountList.add(account);
        } catch (InvalidEntryException e) {
            System.out.println(e.getMessage());
        }
        return accountList;
    }

    @Override
    public String deleteAccount(long accountNumber) {
        String accountNum = String.valueOf(accountNumber);
        String result = null;
        Account accountObj = new Account();

        try {
            if (accountNum.length() != 14) {
                throw new InvalidEntryException("enter valid account number");
            }
            for (Account account : accountList) {
                if (account.getAccountNumber() == accountNumber) {
                    accountObj = account;
                    result = "found";
                }
            }
            if (result == null) {
                throw new DataNotFoundException("data for this account number not found");
            }
        } catch (DataNotFoundException | InvalidEntryException e) {
            return e.getMessage();
        }
        accountList.remove(accountObj);
        return "Deleted successfully";


    }


    public Account updateAccount(long accountNumber, Account account) {
        Account accountObj1 = null;
        for (Account account1 : accountList) {
            if (account1.getAccountNumber() == accountNumber) {
                account1.setaccountHolderName(account.getaccountHolderName());
                account1.setBranch(account.getBranch());
                account1.setMobileNumber(account.getMobileNumber());
                account1.setAccountBalance(account.getAccountBalance());
                account1.setAccountNumber(accountNumber);
                accountObj1 = account1;
                break;
            }
        }
        return accountObj1;
    }

}

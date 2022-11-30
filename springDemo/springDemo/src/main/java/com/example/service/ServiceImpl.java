package com.example.service;

import com.example.entities.Account;
import com.example.exception.DataNotFoundException;
import com.example.exception.EmptyListException;
import com.example.exception.InvalidEntryException;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    List<Account> accountList = new ArrayList<>();

    @Override
    public List<Account> getAllAccounts() throws EmptyListException {
        if(accountList.isEmpty()){
            throw  new EmptyListException("List is empty");
        }
        return accountList;
    }

    @Override
    public Account getAccountByAccountNumber(long accountNumber) throws InvalidEntryException {
        Account account = null;
        for (Account accountObj : accountList) {
            if (accountObj.getAccountNumber() == accountNumber) {
                account = accountObj;
                break;
            }else
                throw new InvalidEntryException("Invalid Account Number");
        }
        return account;
    }

    @Override
    public String addAccount(Account account) throws InvalidEntryException{

//        long mobileNumber = account.getMobileNumber();
//        String phoneNumber = String.valueOf(mobileNumber);
//
//        long accountNumber = account.getAccountNumber();
//        String accountId = String.valueOf(accountNumber);

        String accountNumber =String.valueOf(account.getAccountNumber());
        String mobileNumber=String.valueOf(account.getMobileNumber());
            for(Account accountObj:accountList){
                if (account.getAccountNumber() == accountObj.getAccountNumber())
                    throw new InvalidEntryException("Account Data is already exist");
            }

            if (mobileNumber.length() != 10) {
                throw new InvalidEntryException("Phone number must be a 10 digit long");
            }
            if (accountNumber.length() != 14) {
                throw new InvalidEntryException("Accont number length must be a 14 digit long");
            }
            if (!(account.getaccountHolderName().length() > 4)) {
                throw new InvalidEntryException("Accont Holder name length should  be more than 4 letter");
            } else
                accountList.add(account);

        return "Added Successfully";
    }

    @Override
    public String deleteAccount(long accountNumber) throws DataNotFoundException {
       // String accountNum = String.valueOf(accountNumber);

        for (Account account : accountList) {
            if (account.getAccountNumber() == accountNumber) {
                accountList.remove(account);
                break;
            } else
                throw new DataNotFoundException("data for this account number not found");
        }
        return "Deleted successfully";

    }


    public String updateAccount(long accountNumber, Account account) throws InvalidEntryException {
        for (Account account1 : accountList) {
            if (account1.getAccountNumber() == accountNumber) {
                account1.setAccountNumber((account.getAccountNumber()));
                account1.setaccountHolderName(account.getaccountHolderName());
                account1.setBranch(account.getBranch());
                account1.setMobileNumber(account.getMobileNumber());
                account1.setAccountBalance(account.getAccountBalance());
                account1.setAccountNumber(accountNumber);
                break;
            }
            else{
                throw new InvalidEntryException("Invalid Account number");
            }

        }
        return "Updated successfully";
    }

}

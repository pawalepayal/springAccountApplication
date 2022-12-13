package com.example.service;

import com.example.entities.Account;
import com.example.exception.AccountAlreadyExistException;
import com.example.exception.DataNotFoundException;
import com.example.exception.EmptyListException;
import com.example.exception.InvalidEntryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    List<Account> accountList = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    @Override
    public List<Account> getAllAccounts()  {
        logger.info("method start");
        if (accountList.isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        logger.info("List displayed");
        return accountList;
    }

    @Override
    public Account getAccountByAccountNumber(long accountNumber)  {
        logger.info(" method start");
       // Account account = null;
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountNumber) {
                logger.info("Account Found");
                return account;
            }
               // throw new InvalidEntryException("Invalid Account Number");
        }
        throw new DataNotFoundException("Account not present");
       // return account;
    }

    @Override
    public Account addAccount(Account account)  {

        logger.info("Add method start");
        String accountNumber = String.valueOf(account.getAccountNumber());
        String mobileNumber = String.valueOf(account.getMobileNumber());

        for (Account accountObj : accountList) {
            if (account.getAccountNumber() == accountObj.getAccountNumber())
                throw new AccountAlreadyExistException("Account Data is already exist");
        }

        if (mobileNumber.length() != 10) {
            logger.info("Mobile no. length is not 10 digit");
            throw new InvalidEntryException("Phone number must be a 10 digit long");
        }
        if (accountNumber.length() != 14) {
            logger.info("Account number length is not 14 digit");
            throw new InvalidEntryException("Accont number length must be a 14 digit long");
        }
        if (!(account.getaccountHolderName().length() > 4)) {
            logger.info("Account holder name length is less than 4 letter");
            throw new InvalidEntryException("Accont Holder name length should  be more than 4 letter");
        } else

            accountList.add(account);
        logger.info("account added "+account);
        return account;
    }

    @Override
    public String deleteAccount(long accountNumber) {
        // String accountNum = String.valueOf(accountNumber);
        logger.info("method started.");
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountNumber) {
                accountList.remove(account);
                break;
            } else
                throw new DataNotFoundException("data for this account number not found");
        }
        logger.info("account deleted.");
        return "Deleted successfully";

    }


    public Account updateAccount(long accountNumber, Account account) throws InvalidEntryException {
        logger.info("method started.");
        for (Account account1 : accountList) {
            if (account1.getAccountNumber() == accountNumber) {
                account1.setAccountNumber((account.getAccountNumber()));
                account1.setaccountHolderName(account.getaccountHolderName());
                account1.setBranch(account.getBranch());
                account1.setMobileNumber(account.getMobileNumber());
                account1.setAccountBalance(account.getAccountBalance());
                account1.setAccountNumber(accountNumber);
                break;
            } else {
                throw new InvalidEntryException("Invalid Account number");
            }

        }
        logger.info("account updated."+account);
        return account;
    }

//    public void uniqueCheck(long accountNumber){
//        for(Account account: getAllAccounts()){
//            if(account.getAccountNumber()==accountNumber)
//                throw new AccountAlreadyExistException("Account Already Exist");
//        }
//    }

}

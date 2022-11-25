package com.example.entities;


public class Account {
    private long accountNumber;
    private String accountHolderName;
    private String branch ;
    private long mobileNumber;
    private long accountBalance;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getaccountHolderName() {
        return accountHolderName;
    }
    public void setaccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }


    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }



    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public Account(long accountNumber, String accountHolderName, String branch, long mobileNumber, long accountBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.branch = branch;
        this.mobileNumber = mobileNumber;
        this.accountBalance=accountBalance;
    }
    public Account() {
    }
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", branch='" + branch + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", accountBalance=" + accountBalance +

                '}';
    }







}

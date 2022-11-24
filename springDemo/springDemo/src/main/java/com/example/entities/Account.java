package com.example.entities;

public class Account {
    private int id;
    private String accountHolderName;
    private String branch ;
    private long mobileNumber;

    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getaccountHolderName() {
        return accountHolderName;
    }
    public void setaccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
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


    public Account(int id, String accountHolderName, String branch, long mobileNumber) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.branch = branch;
        this.mobileNumber = mobileNumber;
    }
    public Account() {
    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", branch='" + branch + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }







}

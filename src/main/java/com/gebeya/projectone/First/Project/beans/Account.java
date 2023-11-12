package com.gebeya.projectone.First.Project.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="Account")
public class Account {

    @Id
    @Column(name="ACIF")
    int ACIF;

    @Column(name="CIF")
    int CIF;

    @Column(name="accountNumber")
    long accountNumber;

    @Column(name="balance")
    double balance;

    @Column(name="accountStatus")
    boolean accountStatus;

    @Column(name="createdDate")
    Date createdDate;

    @Column(name="updatedDate")
    Date updatedDate;

    public Account() {
    }

    public Account(int ACIF, int CIF, long accountNumber, double balance, boolean accountStatus, Date createdDate, Date updatedDate) {
        this.ACIF = ACIF;
        this.CIF = CIF;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getACIF() {
        return ACIF;
    }

    public void setACIF(int ACIF) {
        this.ACIF = ACIF;
    }

    public int getCIF() {
        return CIF;
    }

    public void setCIF(int CIF) {
        this.CIF = CIF;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}

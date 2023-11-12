package com.gebeya.projectone.First.Project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gebeya.projectone.First.Project.beans.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private int ACIF;

    private int CIF;

    private long accountNumber;

    private double balance;

    private boolean accountStatus;

    private Date createdDate;

    private Date updatedDate;

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

    public List<AccountDTO> getAllAccountsDTO(List<Account> account) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        AccountDTO accountDTO = new AccountDTO();
        for (Account account1 : account) {
            accountDTO.setAccountNumber(account1.getAccountNumber());
            accountDTO.setAccountStatus(account1.isAccountStatus());
            accountDTO.setBalance(account1.getBalance());

            accountDTOS.add(accountDTO);
        }
        return accountDTOS;
    }

    public AccountDTO addAccountDTO(Account account) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountStatus(account.isAccountStatus());
        accountDTO.setCreatedDate(account.getCreatedDate());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }
    public AccountDTO balanceEnquiryDTO(Account account) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountStatus(account.isAccountStatus());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }
}

package com.gebeya.projectone.First.Project.services;

import com.gebeya.projectone.First.Project.beans.Account;
import com.gebeya.projectone.First.Project.dto.AccountDTO;
import com.gebeya.projectone.First.Project.repositories.AccountRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountDTO accountDTO;

    public List<AccountDTO> getAllAccounts(){
        try {
            List<Account> accounts = accountRepository.findAll();
            return accountDTO.getAllAccountsDTO(accounts);

        }   catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    public List<AccountDTO> getAccountsByCustomerId(int id){
        List<Account> accounts = accountRepository.findAll();
        List<Account> accountList = new ArrayList<>();
        for(Account account: accounts){
            if(account.getCIF() == id){
                accountList.add(account);
            }
        }
            return accountDTO.getAllAccountsDTO(accountList);
    }
    public  AccountDTO addAccount(Account account){
        account.setAccountNumber(getNewAccountNumber());
        account.setAccountStatus(true);
        account.setCreatedDate(new Date());
        return accountDTO.addAccountDTO(accountRepository.save(account));
    }
    public long getNewAccountNumber(){
        List<Account> accounts = accountRepository.findAll();

        long nextNumber =  accounts.get(accounts.size()-1).getAccountNumber() + 1;
        return nextNumber;
    }
    public AccountDTO balanceEnquiry (int customerId){
        Account account = accountRepository.findById(customerId).get();
        return accountDTO.balanceEnquiryDTO(account);
    }
}

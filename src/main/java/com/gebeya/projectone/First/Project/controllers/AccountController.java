package com.gebeya.projectone.First.Project.controllers;

import com.gebeya.projectone.First.Project.beans.Account;
import com.gebeya.projectone.First.Project.dto.AccountDTO;
import com.gebeya.projectone.First.Project.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/api/v1/getAccounts")
    public ResponseEntity<List<AccountDTO>> getAccounts(){
        try{
            List<AccountDTO> accountDTO =accountService.getAllAccounts();
            return new ResponseEntity<List<AccountDTO>>(accountDTO, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/v1/getAccountsByCustomerId/{id}")
    public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(@PathVariable(value = "id") int id){

        try{
            List<AccountDTO> accounts = accountService.getAccountsByCustomerId(id);
            if(!accounts.isEmpty()){
                return new ResponseEntity<List<AccountDTO>>(accounts,HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/api/v1/addAccount")
    public ResponseEntity<AccountDTO> addAccount(@RequestBody Account account){
        try{
            AccountDTO newAccount = accountService.addAccount(account);
            if(!newAccount.getCreatedDate().toString().isEmpty()){
                return new ResponseEntity<AccountDTO>(newAccount,HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/api/v1/balanceEnquiry/{id}")
    public ResponseEntity<AccountDTO> balanceEnquiry(@PathVariable (value = "id") int id){
        try{
            AccountDTO newAccount = accountService.balanceEnquiry(id);
            if(newAccount.getAccountNumber() != 0){
                return new ResponseEntity<AccountDTO>(newAccount,HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

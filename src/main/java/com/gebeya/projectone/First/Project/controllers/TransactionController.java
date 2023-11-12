package com.gebeya.projectone.First.Project.controllers;

import com.gebeya.projectone.First.Project.beans.Transaction;
import com.gebeya.projectone.First.Project.beans.Transfer;
import com.gebeya.projectone.First.Project.dto.TransactionDTO;
import com.gebeya.projectone.First.Project.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/api/v1/customerDeposit")
    public ResponseEntity<TransactionDTO> customerDeposit(@RequestBody Transaction transaction){
        try{
            TransactionDTO transaction1 = transactionService.customerDeposit(transaction);
            if(transaction1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(transaction.getTransactionCode().equalsIgnoreCase("NOT ALLOWED"))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            else if(transaction.getResponseCode() == 0){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<TransactionDTO>(transaction1,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/api/v1/merchantDeposit/{id}")
    public ResponseEntity<TransactionDTO> merchantDeposit(@RequestBody Transaction transaction, @PathVariable(value = "id") int id){
        try{
            TransactionDTO transaction1 = transactionService.merchantDeposit(transaction,id);
            if(transaction1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(transaction1.getTransactionCode().equalsIgnoreCase("NOT ALLOWED") && transaction1.getStatus().equalsIgnoreCase("expired"))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            else if(transaction1.getResponseCode() == 0){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<TransactionDTO>(transaction1,HttpStatus.OK);
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/api/v1/customerWithdraw")
    public ResponseEntity<TransactionDTO> customerWithdraw(@RequestBody Transaction transaction){
        try{
            TransactionDTO transaction1 = transactionService.customerWithdrawal(transaction);
            if(transaction1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(transaction1.getTransactionCode().equalsIgnoreCase("NOT ALLOWED"))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            else if(transaction1.getResponseCode() == 0){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<TransactionDTO>(transaction1,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/api/v1/merchantWithdraw/{id}")
    public ResponseEntity<TransactionDTO> merchantWithdraw(@PathVariable (value = "id")int id ,@RequestBody Transaction transaction){
        try{
            TransactionDTO transaction1 = transactionService.merchantWithDraw(transaction,id);
            if(transaction1==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(transaction1.getTransactionCode().equalsIgnoreCase("NOT ALLOWED"))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            else if(transaction1.getResponseCode() == 0){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<TransactionDTO>(transaction1,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/api/v1/transfer")
    public ResponseEntity<TransactionDTO> transfer (@RequestBody Transfer transfer){
        try{
            TransactionDTO transaction = transactionService.transfer(transfer);
            if(transaction == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else if(transaction.getRemark().equalsIgnoreCase("insufficient balance")){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            else{
                return new ResponseEntity<TransactionDTO>(transaction,HttpStatus.OK);
            }

        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/api/v1/shortStatement/{id}")
    public ResponseEntity<List<TransactionDTO>> shortStatement (@PathVariable (value = "id") int id){
        try{
            List<TransactionDTO> transaction = transactionService.shortStatement(id);
            if(transaction.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<List<TransactionDTO>>(transaction,HttpStatus.OK);
            }

        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

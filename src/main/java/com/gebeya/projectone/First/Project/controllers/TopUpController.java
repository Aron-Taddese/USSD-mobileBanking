package com.gebeya.projectone.First.Project.controllers;

import com.gebeya.projectone.First.Project.beans.Account;
import com.gebeya.projectone.First.Project.beans.TopUp;
import com.gebeya.projectone.First.Project.services.TopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopUpController {

    @Autowired
    TopUpService topUpService;

    @PostMapping("/api/v1/topUp/{id}")
    public ResponseEntity<TopUp> topUp(@PathVariable (value = "id") int id, @RequestParam  int accountId){
        try{
            TopUp topUp = topUpService.topUpMobileCard(accountId,id);
            return new ResponseEntity<TopUp>(topUp,HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

package com.gebeya.projectone.First.Project.controllers;

import com.gebeya.projectone.First.Project.beans.Customer;
import com.gebeya.projectone.First.Project.beans.MobileBankingUser;
import com.gebeya.projectone.First.Project.dto.CustomerDTO;
import com.gebeya.projectone.First.Project.dto.MobileBankingUserDTO;
import com.gebeya.projectone.First.Project.services.MobileBankingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
public class MobileBankingUserController {

    @Autowired
    MobileBankingUserService mobileBankingUserService;

    @GetMapping("/api/v1/getAllMobileBankingUsers")
    public ResponseEntity<List<CustomerDTO>> getAllMobileBankingUsers(){
        try{
            List<CustomerDTO> mobileBankingUsers = mobileBankingUserService.getAllMobileBankingUsers();
            if(!mobileBankingUsers.isEmpty()){
                return new ResponseEntity<List<CustomerDTO>>(mobileBankingUsers, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/api/v1/countMobileBankingUsers")
    public ResponseEntity<Integer> countMobileBankingUsers(){
        try{
            return new ResponseEntity<Integer>(mobileBankingUserService.countMobileBankingUsers(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/v1/getAllMerchantMobileBankingUsers")
    public ResponseEntity<List<CustomerDTO>> getAllMerchantMobileBankingUsers(){
        try{
            List<CustomerDTO> mobileBankingUsers = mobileBankingUserService.getAllMerchantMobileBankingUsers();
            if(!mobileBankingUsers.isEmpty()){
                return new ResponseEntity<List<CustomerDTO>>(mobileBankingUsers, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/api/v1/addMobileBankingUser")
    public ResponseEntity <MobileBankingUserDTO> addMobileBankingUser(@RequestBody MobileBankingUser mobileBankingUser){
        try {
            MobileBankingUserDTO bankingUser1 = mobileBankingUserService.addMobileBankingUser(mobileBankingUser);
            if (bankingUser1.getProfile().equalsIgnoreCase("User already added")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            } else if (mobileBankingUser.getProfile().equalsIgnoreCase("user not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<MobileBankingUserDTO>(bankingUser1, HttpStatus.OK);
            }
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/api/v1/updateMobileBankingUser/{id}")
    public ResponseEntity<MobileBankingUserDTO> updateMobileBankingUser(@PathVariable(value = "id") int id, @RequestBody MobileBankingUser MobileBankingUser){
    try{
        MobileBankingUserDTO bankingUser = mobileBankingUserService.updateMobileBankingUser(MobileBankingUser,id);
        if(bankingUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MobileBankingUserDTO>(bankingUser, HttpStatus.OK);
    }
    catch (Exception ex){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    }

}

package com.gebeya.projectone.First.Project.services;

import com.gebeya.projectone.First.Project.beans.Customer;
import com.gebeya.projectone.First.Project.beans.MobileBankingUser;
import com.gebeya.projectone.First.Project.dto.CustomerDTO;
import com.gebeya.projectone.First.Project.dto.MobileBankingUserDTO;
import com.gebeya.projectone.First.Project.repositories.CustomerRepository;
import com.gebeya.projectone.First.Project.repositories.MobileBankingUserRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Service
public class MobileBankingUserService {

    @Autowired
    MobileBankingUserRepository mobileBankingUserRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerDTO customerDTO;
    @Autowired
    MobileBankingUserDTO mobileBankingUserDTO;

    public List<CustomerDTO> getAllMobileBankingUsers(){
        List<Customer> customers = new ArrayList<>();
        List<MobileBankingUser> mobileBankingUsers = mobileBankingUserRepository.findAll();
        for(MobileBankingUser bankingUser: mobileBankingUsers){
            Customer customer = customerRepository.findById(bankingUser.getCIF()).get();
            if(!customer.getFirstName().isEmpty()){
                customers.add(customer);
            }
        }
        return customerDTO.getAllCustomersDTO(customers);
    }
    public List<CustomerDTO> getAllMerchantMobileBankingUsers(){
        List<Customer> customers = new ArrayList<>();
        List<MobileBankingUser> mobileBankingUsers = mobileBankingUserRepository.findAll();
         List<MobileBankingUser> mobileBankingUserList = null;
         for(MobileBankingUser bankingUser:mobileBankingUsers){
             if(bankingUser.getProfile().equalsIgnoreCase("merchant")){
                 mobileBankingUserList.add(bankingUser);
             }
         }
         if(mobileBankingUserList.isEmpty()){
             return null;
         }
         else{
             for(MobileBankingUser bankingUser: mobileBankingUserList) {
                 Customer customer = customerRepository.findById(bankingUser.getCIF()).get();
                 if (!customer.getFirstName().isEmpty()) {
                     customers.add(customer);
                 }
                 return customerDTO.getAllCustomersDTO(customers);
             }
             return  null;
         }

    }

    public MobileBankingUserDTO addMobileBankingUser(MobileBankingUser mobileBankingUser){
        Customer customer = customerRepository.findById(mobileBankingUser.getCIF()).get();
        if(!customer.getFirstName().isEmpty()){
            MobileBankingUser bankingUser = null;
           List<MobileBankingUser> mobileBankingUsers =   mobileBankingUserRepository.findAll();
           for (MobileBankingUser mobileBankingUser1: mobileBankingUsers){
               if(mobileBankingUser1.getCIF() == customer.getCIF()){
                   bankingUser = mobileBankingUser1;
               }
           }
           if(bankingUser == null){
                mobileBankingUser.setPassword("1234");
                mobileBankingUser.setRegisteredDate(new Date());
                return mobileBankingUserDTO.addMobileBankingUserDTO(mobileBankingUserRepository.save(mobileBankingUser));
            }else{
                mobileBankingUser.setProfile("User already added");
                return mobileBankingUserDTO.addMobileBankingUserDTO(mobileBankingUser);
            }
          //  return mobileBankingUserRepository.save(mobileBankingUser);
        }
        mobileBankingUser.setProfile("User not found");

        return  mobileBankingUserDTO.addMobileBankingUserDTO(mobileBankingUser);
    }
    public int countMobileBankingUsers (){
        return mobileBankingUserRepository.findAll().size();
    }

    public MobileBankingUserDTO updateMobileBankingUser(MobileBankingUser mobileBankingUser, int id){
        List<MobileBankingUser> bankingUser = mobileBankingUserRepository.findAll();
        MobileBankingUser bankingUser1 = null;
        for(MobileBankingUser bankingUser2: bankingUser){
            if(bankingUser2.getCIF() == id)
                bankingUser1 = bankingUser2;
        }
        if(bankingUser1==null){
            return null;
        }
        else{
            MobileBankingUser existingUser = mobileBankingUserRepository.findById(bankingUser1.getCIF()).get();
            if(!mobileBankingUser.getProfile().isEmpty()){
                existingUser.setProfile(mobileBankingUser.getProfile());
            }
            if(mobileBankingUser.getPassword() != null){
                existingUser.setPassword(mobileBankingUser.getPassword());
            }
            if(mobileBankingUser.getMobileNumber() > 1){
                existingUser.setMobileNumber(mobileBankingUser.getMobileNumber());
            }
           if(!mobileBankingUser.getLang().isEmpty()){
               existingUser.setLang(mobileBankingUser.getLang());
           }
            return mobileBankingUserDTO.updateMobileBankingUserDTO(mobileBankingUserRepository.save(existingUser));
        }
    }
}

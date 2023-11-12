package com.gebeya.projectone.First.Project.controllers;

import com.gebeya.projectone.First.Project.beans.Customer;
import com.gebeya.projectone.First.Project.config.LocaleConfig;
import com.gebeya.projectone.First.Project.dto.CustomerDTO;
import com.gebeya.projectone.First.Project.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerDTO customerDTO;

    @Autowired
    ResourceBundleMessageSource messageSource;

    @GetMapping("/api/v1/getAllCustomers")
    public ResponseEntity<List<CustomerDTO>>  getAllCustomers(){
        try{

            List<CustomerDTO> customerDTOS =  customerService.getAllCustomers();
            return new ResponseEntity<List<CustomerDTO>>(customerDTOS, HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


    }

    @GetMapping("/api/v1/getCustomerById/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(value = "id") int id){
        try{
            CustomerDTO customer = customerService.getCustomerById(id);
            return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/v1/getCustomerById/customerName")
    public ResponseEntity<CustomerDTO> getCustomerByName(@RequestParam(value = "name") String name){
        try{
            CustomerDTO customer = customerService.getCustomerByName(name);
            return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/v1/addCustomer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer customer){
       try {
           CustomerDTO customer2 =  customerService.addCustomer(customer);
           if(customer2 != null){
               return new ResponseEntity<CustomerDTO>(customer2,HttpStatus.OK);
           }
           else
               return new ResponseEntity<>(HttpStatus.FORBIDDEN);

       }catch (Exception ex){
           return new ResponseEntity<>(HttpStatus.CONFLICT);
       }
    }

    @PutMapping("/api/v1/updateCustomer/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable (value="id") int id, @RequestBody Customer customer){
        try{

            CustomerDTO customer1 = customerService.updateCustomer(customer,id);
            if(!(customer1.getFirstName().isEmpty())){
                return new ResponseEntity<CustomerDTO>(customer1,HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/",method =  RequestMethod.GET)
    public String[] getSource(@RequestHeader("Accept-Language") String locale){
        Locale locale1 = new Locale(locale);
        String[] messages = new String[8];
        for(int i= 0 ; i < messages.length; i++){
           String messageKey = "message" + i;
           messages[i] = messageSource.getMessage(messageKey,null,locale1);
        }
        return messages;

       // return messageSource().getMessage("welcome.txt",null, new Locale(locale));
    }
}

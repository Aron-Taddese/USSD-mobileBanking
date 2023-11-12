package com.gebeya.projectone.First.Project.services;

import com.gebeya.projectone.First.Project.beans.Customer;
import com.gebeya.projectone.First.Project.dto.CustomerDTO;
import com.gebeya.projectone.First.Project.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerDTO customerDTO;

    public List<CustomerDTO> getAllCustomers(){
        try {
            List<Customer> customers = customerRepository.findAll();
            return customerDTO.getAllCustomersDTO(customers);


        }   catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public CustomerDTO getCustomerById(int id){
        Customer customer = customerRepository.findById(id).get();
        return customerDTO.getCustomerByIdDTO(customer);
    }

    public CustomerDTO getCustomerByName(String name){
        List<Customer> customerList = customerRepository.findAll();
        Customer customer = null;
        for(Customer cus: customerList){
            if(cus.getFirstName().equals(name)){
                customer = cus;
            }
        }
        return  customerDTO.getCustomerByIdDTO(customer);
    }
    public CustomerDTO addCustomer(Customer customer) {

        List<Customer> customer1 = customerRepository.findAll();
        boolean isExisted = false;
        for (Customer cus : customer1) {
            if (customer.getFirstName().equalsIgnoreCase(cus.getFirstName()) &&
                customer.getMiddleName().equalsIgnoreCase(cus.getMiddleName()) &&
                customer.getLastName().equalsIgnoreCase(cus.getLastName())&&
                customer.getEmail().equalsIgnoreCase(cus.getEmail())
            ) {
                isExisted = true;
            }
        }
        if(!isExisted){
            customer.setCIF(getMaxId());
            customerRepository.save(customer);
            return customerDTO.addCustomerDTO(customer);
        }
        else
            return null;
    }
    public  int getMaxId()
    {
        return customerRepository.findAll().size()+1;
    }

    public CustomerDTO updateCustomer(Customer customer, int id){
        Customer existCustomer = customerRepository.findById(id).get();
        if(existCustomer.getFirstName().isEmpty()){
            return null;
        }
        else{
            if(!customer.getCity().isEmpty()){
                existCustomer.setCity(customer.getCity());
            }
            if(!customer.getCountry().isEmpty()){
                existCustomer.setCountry(customer.getCountry());
            }
            if(!customer.getDOB().toString().isEmpty()){
                existCustomer.setDOB(customer.getDOB());
            }
            if(!customer.getEmail().isEmpty()){
                existCustomer.setEmail(customer.getEmail());
            }
            if(!customer.getFirstName().isEmpty()){
                existCustomer.setFirstName(customer.getFirstName());
            }
            if(!customer.getLastName().isEmpty()){
                existCustomer.setLastName(customer.getLastName());
            }
            if(!customer.getMiddleName().isEmpty()){
                existCustomer.setMiddleName(customer.getMiddleName());
            }
            if(!customer.getSalutation().isEmpty()){
                existCustomer.setSalutation(customer.getSalutation());
            }
            if(!customer.getHomePhone().isEmpty()){
                existCustomer.setHomePhone(customer.getHomePhone());
            }
            if(!customer.getMobileNo().isEmpty()){
                existCustomer.setMobileNo(customer.getMobileNo());
            }
            if(!customer.getState().isEmpty()){
                existCustomer.setState(customer.getState());
            }
            if(customer.getPostalCode() > 1){
                existCustomer.setPostalCode(customer.getPostalCode());
            }
            if(!customer.getHomePostalAddress().isEmpty()){
                existCustomer.setHomePostalAddress(customer.getHomePostalAddress());
            }
            customerRepository.save(existCustomer);
            return customerDTO.updateCustomerDTO(existCustomer);
        }
    }
}

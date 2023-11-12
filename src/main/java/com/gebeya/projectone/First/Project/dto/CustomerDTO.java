package com.gebeya.projectone.First.Project.dto;

import com.gebeya.projectone.First.Project.beans.Customer;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    private int cif;

    private String firstName;

    private String salutation;

    private String lastName;

    private String middleName;

    private String email;

    private Date DOB;

    String homePostalAddress;

    String city;

    String state;

    int postalCode;

    String country;

    String homePhone;

    String mobileNo;

    public int getCif() {
        return cif;
    }

    public void setCif(int cif) {
        this.cif = cif;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getHomePostalAddress() {
        return homePostalAddress;
    }

    public void setHomePostalAddress(String homePostalAddress) {
        this.homePostalAddress = homePostalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public List<CustomerDTO> getAllCustomersDTO(List<Customer> customers){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        CustomerDTO dto = new CustomerDTO();
        for(Customer customerD: customers){
            dto.setFirstName(customerD.getFirstName());
            dto.setMiddleName(customerD.getMiddleName());
            dto.setLastName(customerD.getLastName());
            dto.setSalutation(customerD.getSalutation());
            dto.setEmail(customerD.getEmail());
            dto.setDOB(customerD.getDOB());
            dto.setHomePostalAddress(customerD.getHomePostalAddress());
            dto.setCountry(customerD.getCountry());
            dto.setCity(customerD.getCity());
            dto.setState(customerD.getState());
            dto.setPostalCode(customerD.getPostalCode());
            dto.setHomePhone(customerD.getHomePhone());
            dto.setMobileNo(customerD.getMobileNo());

            customerDTOS.add(dto);

        }
        return customerDTOS;
    }
    public CustomerDTO getCustomerByIdDTO(Customer customer){

        CustomerDTO dto = new CustomerDTO();

            dto.setFirstName(customer.getFirstName());
            dto.setMiddleName(customer.getMiddleName());
            dto.setLastName(customer.getLastName());
            dto.setSalutation(customer.getSalutation());
            dto.setEmail(customer.getEmail());
            dto.setDOB(customer.getDOB());
            dto.setHomePostalAddress(customer.getHomePostalAddress());
            dto.setCountry(customer.getCountry());
            dto.setCity(customer.getCity());
            dto.setState(customer.getState());
            dto.setPostalCode(customer.getPostalCode());
            dto.setHomePhone(customer.getHomePhone());
            dto.setMobileNo(customer.getMobileNo());
        return dto;
    }
    public CustomerDTO addCustomerDTO(Customer customer){

        CustomerDTO dto = new CustomerDTO();
        dto.setCif(customer.getCIF());
        dto.setFirstName(customer.getFirstName());
        dto.setMiddleName(customer.getMiddleName());
        dto.setLastName(customer.getLastName());
        return dto;
    }
    public CustomerDTO updateCustomerDTO(Customer customer){

        CustomerDTO dto = new CustomerDTO();

        dto.setCif(customer.getCIF());
        dto.setFirstName(customer.getFirstName());
        dto.setMiddleName(customer.getMiddleName());
        dto.setLastName(customer.getLastName());
        dto.setSalutation(customer.getSalutation());
        dto.setEmail(customer.getEmail());
        dto.setDOB(customer.getDOB());
        dto.setHomePostalAddress(customer.getHomePostalAddress());
        dto.setCountry(customer.getCountry());
        dto.setCity(customer.getCity());
        dto.setState(customer.getState());
        dto.setPostalCode(customer.getPostalCode());
        dto.setHomePhone(customer.getHomePhone());
        dto.setMobileNo(customer.getMobileNo());
        return dto;
    }
}

package com.gebeya.projectone.First.Project.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @Column(name="CIF")
    int CIF;

    @Column(name="first_name")
    String firstName;

    @Column(name="salutation")
    String salutation;

    @Column(name="last_name")
    String lastName;

    @Column(name="middle_name")
    String middleName;

    @Column(name="email")
    String email;

    @Column(name="DOB")
    Date DOB;

    @Column(name="home_postal_address")
    String homePostalAddress;

    @Column(name="city")
    String city;

    @Column(name="state")
    String state;

    @Column(name="postal_code")
    int postalCode;

    @Column(name="country")
    String country;

    @Column(name="home_phone")
    String homePhone;

    @Column(name="mobile_no")
    String mobileNo;

    public Customer(){

    }

    public Customer(int CIF, String firstName, String salutation, String lastName, String middleName, String email, Date DOB, String homePostalAddress, String city, String state, int postalCode, String country, String homePhone, String mobileNo) {
        this.CIF = CIF;
        this.firstName = firstName;
        this.salutation = salutation;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.DOB = DOB;
        this.homePostalAddress = homePostalAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.mobileNo = mobileNo;
    }

    public int getCIF() {
        return CIF;
    }

    public void setCIF(int CIF) {
        this.CIF = CIF;
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
}

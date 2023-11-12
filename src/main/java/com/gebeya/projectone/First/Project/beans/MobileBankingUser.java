package com.gebeya.projectone.First.Project.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="mobilebankinguser")
public class MobileBankingUser {

    @Id
    @Column(name="ID")
    int ID;

    @Column(name="CIF")
    int CIF;

    @Column(name="mobilenumber")
    int mobileNumber;

    @Column(name="Password")
    String password;

    @Column(name="lang")
    String lang;

    @Column(name="profile")
    String profile;

    @Column(name="registereddate")
    Date registeredDate;

    public MobileBankingUser() {
    }

    public MobileBankingUser(int ID, int CIF, int mobileNumber, String password, String lang, String profile, Date registeredDate) {
        this.ID = ID;
        this.CIF = CIF;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.lang = lang;
        this.profile = profile;
        this.registeredDate = registeredDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCIF() {
        return CIF;
    }

    public void setCIF(int CIF) {
        this.CIF = CIF;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}

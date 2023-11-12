package com.gebeya.projectone.First.Project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gebeya.projectone.First.Project.beans.MobileBankingUser;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MobileBankingUserDTO {
    int ID;

    int CIF;

    int mobileNumber;

    String password;

    String lang;

    String profile;

    Date registeredDate;

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

    public MobileBankingUserDTO addMobileBankingUserDTO(MobileBankingUser mobileBankingUser){
        MobileBankingUserDTO dto = new MobileBankingUserDTO();
        dto.setMobileNumber(getMobileNumber());
        dto.setPassword(getPassword());
        dto.setProfile(getProfile());
        dto.setLang(getLang());
        dto.setRegisteredDate(getRegisteredDate());

        return dto;
    }

    public MobileBankingUserDTO updateMobileBankingUserDTO(MobileBankingUser mobileBankingUser){
        MobileBankingUserDTO dto = new MobileBankingUserDTO();
        dto.setMobileNumber(getMobileNumber());
        dto.setPassword(getPassword());
        dto.setProfile(getProfile());
        dto.setLang(getLang());
        dto.setRegisteredDate(getRegisteredDate());
        dto.setID(getID());

        return dto;
    }
}

package com.gebeya.projectone.First.Project.beans;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @Column(name = "RRN")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "paramsSequence")
    int RRN;

    @Column(name = "transactionCode")
    String transactionCode;

    @Column(name = "ACIF")
    int ACIF;

    @Column(name = "side")
    String side;

    @Column(name = "amount")
    double amount;

    @Column(name = "remark")
    String remark;

    @Column(name = "otp")
    int otp;

    @Column(name = "status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRRN() {
        return RRN;
    }

    public void setRRN(int RRN) {
        this.RRN = RRN;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public int getACIF() {
        return ACIF;
    }

    public void setACIF(int ACIF) {
        this.ACIF = ACIF;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Column(name = "responseCode")
    int responseCode;

    @Column(name = "transactionDate")
    Date transactionDate;
}

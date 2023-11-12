package com.gebeya.projectone.First.Project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gebeya.projectone.First.Project.beans.Transaction;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class TransactionDTO {

    private int RRN;

    private String transactionCode;

    private int ACIF;

    private String side;

    private double amount;

    private String remark;

    private int otp;

    private String status;
    private int responseCode;

    private Date transactionDate;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public TransactionDTO customerDepositDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setTransactionCode(transaction.getTransactionCode());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setOtp(transaction.getOtp());
        transactionDTO.setStatus(transaction.getStatus());
        transactionDTO.setRemark(transaction.getRemark());
        transactionDTO.setTransactionDate(getTransactionDate());
        transactionDTO.setResponseCode(transaction.getResponseCode());
        return transactionDTO;
    }

    public TransactionDTO merchantDepositDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setTransactionCode(transaction.getTransactionCode());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setStatus(transaction.getStatus());
        transactionDTO.setRemark(transaction.getRemark());
        transactionDTO.setSide(transaction.getSide());
        transactionDTO.setTransactionCode(transaction.getTransactionCode());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        transactionDTO.setResponseCode(transaction.getResponseCode());
        return transactionDTO;
    }
    public List<TransactionDTO> shortStatementDTO(List<Transaction> transaction) {

        TransactionDTO transactionDTO = new TransactionDTO();
        List<TransactionDTO> transactionList = new ArrayList<>();
        for(Transaction tr: transaction){
            transactionDTO.setTransactionCode(tr.getTransactionCode());
            transactionDTO.setAmount(tr.getAmount());
            transactionDTO.setStatus(tr.getStatus());
            transactionDTO.setRemark(tr.getRemark());
            transactionDTO.setSide(tr.getSide());
            transactionDTO.setTransactionDate(tr.getTransactionDate());
            transactionDTO.setResponseCode(tr.getResponseCode());

            transactionList.add(transactionDTO);
        }

        return transactionList;
    }
}

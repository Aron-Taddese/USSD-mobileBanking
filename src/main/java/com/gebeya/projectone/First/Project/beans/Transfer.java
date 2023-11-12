package com.gebeya.projectone.First.Project.beans;

public class Transfer {
    int fromAccountCIF;
    int toAccountCIF;
    double amount;
    int otp;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getFromAccountCIF() {
        return fromAccountCIF;
    }

    public void setFromAccountCIF(int fromAccountCIF) {
        this.fromAccountCIF = fromAccountCIF;
    }

    public int getToAccountCIF() {
        return toAccountCIF;
    }

    public void setToAccountCIF(int toAccountCIF) {
        this.toAccountCIF = toAccountCIF;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Transfer(int fromAccountCIF, int toAccountCIF, double amount) {
        this.fromAccountCIF = fromAccountCIF;
        this.toAccountCIF = toAccountCIF;
        this.amount = amount;
    }
}

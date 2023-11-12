package com.gebeya.projectone.First.Project.services;

import com.gebeya.projectone.First.Project.beans.Account;
import com.gebeya.projectone.First.Project.beans.MobileBankingUser;
import com.gebeya.projectone.First.Project.beans.Transaction;
import com.gebeya.projectone.First.Project.beans.Transfer;
import com.gebeya.projectone.First.Project.dto.TransactionDTO;
import com.gebeya.projectone.First.Project.repositories.AccountRepository;
import com.gebeya.projectone.First.Project.repositories.MobileBankingUserRepository;
import com.gebeya.projectone.First.Project.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MobileBankingUserRepository mobileBankingUserRepository;

    @Autowired
    TransactionDTO transactionDTO;

    public Transaction deposit(Transaction transaction) {

        Account account = accountRepository.findById(transaction.getACIF()).get();
        if (account != null) {
            if (isMobileBankingUser(account.getCIF())) {
                double currentBalance = account.getBalance();
                account.setBalance(currentBalance + transaction.getAmount());
                account.setUpdatedDate(new Date());
                accountRepository.save(account);

                transaction.setTransactionCode(generateTransactionCode());
                transaction.setSide("Credit");
                transaction.setTransactionDate(new Date());
                transaction.setResponseCode(1);
                return transactionRepository.save(transaction);
            } else {
                transaction.setResponseCode(0);
                return transaction;
            }
        } else {
            return null;
        }
    }

    public TransactionDTO customerWithdrawal(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getACIF()).get();
        if (account != null) {
            if (isMobileBankingUser(account.getCIF())) {
                double currentBalance = account.getBalance();
                if (currentBalance - transaction.getAmount() > 25.00) {

                    transaction.setTransactionCode(generateTransactionCode());
                    transaction.setSide("Debit");
                    transaction.setAmount(transaction.getAmount());
                    transaction.setOtp(generateOTP());
                    transaction.setTransactionDate(new Date());
                    transaction.setResponseCode(1);
                    transaction.setStatus("pending");
                    transaction.setRemark("Pending to withdraw money, Please go to your nearest Agent and withdraw your money.");
                    return transactionDTO.customerDepositDTO(transactionRepository.save(transaction));
                } else {
                    transaction.setResponseCode(0);
                    return transactionDTO.customerDepositDTO(transaction);
                }
            } else {
                transaction.setTransactionCode("NOT ALLOWED");
                return transactionDTO.customerDepositDTO(transaction);
            }
        } else {
            return null;
        }
    }

    public TransactionDTO merchantWithDraw(Transaction transaction, int merchantId) {
        Account account = accountRepository.findById(transaction.getACIF()).get();
        Transaction transaction1 = getPendingTransactionDetails(transaction.getACIF(),transaction.getOtp());

        if (account != null) {
            if (isMobileBankingUser(account.getCIF()) && isMerchantBankingUser(merchantId)) {
                double currentBalance = account.getBalance();
                if (currentBalance - transaction1.getAmount() > 25.00) {
                    if (!checkOtpExpiration(transaction1.getTransactionDate())) {
                        transaction1.setStatus("Expired");
                        return transactionDTO.merchantDepositDTO(transaction1);
                    }
                    if (transaction1.getStatus().equalsIgnoreCase("success")) {
                        transaction1.setStatus("Already done!");
                        transaction1.setRemark("The Transaction is already completed! please try another transaction");
                        return transactionDTO.merchantDepositDTO(transaction1);
                    }
                    account.setBalance(currentBalance - transaction1.getAmount());
                    account.setUpdatedDate(new Date());
                    accountRepository.save(account);

                    plus(merchantId, transaction1.getAmount(), account.getAccountNumber());

                    return transactionDTO.merchantDepositDTO(updateTransaction(transaction1.getRRN()));

                } else {
                    transaction1.setResponseCode(0);
                    return transactionDTO.merchantDepositDTO(transaction1);
                }
            } else {
                transaction1.setTransactionCode("NOT ALLOWED");
                return transactionDTO.merchantDepositDTO(transaction1);
            }
        } else {
            return null;
        }
    }

    public boolean isMobileBankingUser(int accountNumber) {
        boolean isMobileBankingUser = false;
        List<MobileBankingUser> mobileBankingUserList = mobileBankingUserRepository.findAll();
        MobileBankingUser mobileBankingUser = null;
        for (MobileBankingUser bankingUser : mobileBankingUserList) {
            if (bankingUser.getCIF() == accountNumber) {
                isMobileBankingUser = true;
            }
        }
        return isMobileBankingUser;
    }
    public boolean isMerchantBankingUser(int customerId) {
        boolean isMerchantUser = false;
        List<MobileBankingUser> mobileBankingUserList = mobileBankingUserRepository.findAll();
        MobileBankingUser mobileBankingUser = null;
        for (MobileBankingUser bankingUser : mobileBankingUserList) {
            if (bankingUser.getProfile().equalsIgnoreCase("merchant") && bankingUser.getCIF() == customerId) {
                isMerchantUser = true;
            }
        }
        return isMerchantUser;
    }

    private Transaction updateTransaction(int id) {
        Transaction transaction = transactionRepository.findById(id).get();


        transaction.setSide("Debit");
        transaction.setStatus("Success");
        transaction.setResponseCode(1);
        transaction.setRemark("Withdraw money from Bank Agent (Merchant)");
        transactionRepository.save(transaction);
        return transaction;
    }

    private Transaction updateDepositTransaction(int id) {
        Transaction transaction = transactionRepository.findById(id).get();


        transaction.setSide("Credit");
        transaction.setStatus("Success");
        transaction.setResponseCode(1);
        transaction.setRemark("Deposit"+ transaction.getAmount()+" ETB to Bank Agent (Merchant)");
        transactionRepository.save(transaction);
        return transaction;
    }

    public String generateTransactionCode() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public TransactionDTO transfer(Transfer transfer) {
        Account account = accountRepository.findById(transfer.getFromAccountCIF()).get();
        Account account2 = accountRepository.findById(transfer.getToAccountCIF()).get();

        Transaction transaction = new Transaction();

        if (account != null && account2 != null) {
            if (transfer.getAmount() < account.getBalance()) {
                Transaction transaction1 = minus(account, transfer.getAmount(), account2.getAccountNumber());
                Account account1 = new Account();

                Transaction transaction2 = plus(transfer.getToAccountCIF(), transfer.getAmount(), account.getAccountNumber());
                return transactionDTO.merchantDepositDTO(transaction1 != null ? transaction1 : transaction2);

            } else transaction.setRemark("insufficient balance");
            return transactionDTO.merchantDepositDTO(transaction);
        }
        return null;
    }

    public TransactionDTO customerDeposit(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getACIF()).get();
        if (account != null) {
            if (isMobileBankingUser(account.getCIF())) {
                transaction.setTransactionCode(generateTransactionCode());
                transaction.setSide("Credit");
                transaction.setAmount(transaction.getAmount());
                transaction.setOtp(generateOTP());
                transaction.setTransactionDate(new Date());
                transaction.setResponseCode(1);
                transaction.setStatus("pending");
                transaction.setRemark("Pending to Deposit money, Please go to your nearest Agent and Deposite into your account.");
                return transactionDTO.customerDepositDTO(transactionRepository.save(transaction));

            }
        }
        return null;
    }

    public TransactionDTO merchantDeposit(Transaction transaction, int merchantId) {
        Account account = accountRepository.findById(merchantId).get();
        Transaction pendingTransaction = getPendingTransactionDetails(transaction.getACIF(), transaction.getOtp());

        if (account != null) {
            if (isMobileBankingUser(account.getCIF())) {
                double currentBalance = account.getBalance();
                if (currentBalance < pendingTransaction.getAmount()) {
                    pendingTransaction.setStatus("Agent have no sufficent balance!");
                    return transactionDTO.merchantDepositDTO(pendingTransaction);
                }
                if (!checkOtpExpiration(pendingTransaction.getTransactionDate())) {
                    pendingTransaction.setStatus("Expired");
                    return transactionDTO.merchantDepositDTO(pendingTransaction);
                }
                if (pendingTransaction.getStatus().equalsIgnoreCase("success")) {
                    pendingTransaction.setStatus("Already done!");
                    pendingTransaction.setRemark("The Transaction is already completed! please try another transaction");
                    return transactionDTO.merchantDepositDTO(pendingTransaction);
                }
                minus(account, pendingTransaction.getAmount(), pendingTransaction.getACIF());

                Account account1 = getAccountDetails(transaction.getACIF());

                        account.setBalance(account1.getBalance() + pendingTransaction.getAmount());
                account.setUpdatedDate(new Date());
                accountRepository.save(account);

                return transactionDTO.merchantDepositDTO(updateDepositTransaction(pendingTransaction.getRRN()));

            } else {
                pendingTransaction.setTransactionCode("NOT ALLOWED");
                return transactionDTO.merchantDepositDTO(pendingTransaction);
            }
        } else {
            return null;
        }

    }

    private Transaction minus(Account account, double amount, long accountNumber) {

        Transaction transaction = new Transaction();
        account.setBalance(account.getBalance() - amount);
        account.setUpdatedDate(new Date());
        accountRepository.save(account);

        transaction.setAmount(amount);
        transaction.setSide("Debit");
        transaction.setTransactionCode(generateTransactionCode());
        transaction.setACIF(account.getACIF());
        transaction.setResponseCode(1);
        transaction.setTransactionDate(new Date());
        transaction.setStatus("Success");
        transaction.setRemark("Transferred from your account number, " + account.getAccountNumber() + " to " +
                accountNumber + " -> " + amount + " ETB");
        transactionRepository.save(transaction);

        return transaction;
    }

    private Transaction plus(int accountTo, double amount, long accountNumber) {
        Account account = accountRepository.findById(accountTo).get();
        Transaction transaction = new Transaction();
        List<Transaction> rnn = transactionRepository.findAll();
        int newRnn = 0;
        for (Transaction transaction1 : rnn) {
            newRnn++;
        }
        account.setBalance(account.getBalance() + amount);
        account.setUpdatedDate(new Date());
        accountRepository.save(account);
        transaction.setRRN(newRnn + 1);
        transaction.setAmount(amount);
        transaction.setSide("Debit");
        // transaction.setOtp(generateOTP());
        transaction.setTransactionCode(generateTransactionCode());
        transaction.setACIF(account.getACIF());
        transaction.setResponseCode(1);
        transaction.setStatus("Success");
        transaction.setTransactionDate(new Date());
        transaction.setRemark("Transferred from account number, " + accountNumber + " to your Account Number " +
                account.getAccountNumber() + " -> " + amount + " ETB");
        transactionRepository.save(transaction);

        return transaction;
    }

    private int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    private boolean checkOtpExpiration(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int totalMinutes = hour * 60 + minute;

        int hourCurrent = calendar1.get(Calendar.HOUR_OF_DAY);
        int minuteCurrent = calendar1.get(Calendar.MINUTE);
        int totalMinutesCurrent = hourCurrent * 60 + minuteCurrent;
        return totalMinutesCurrent - totalMinutes < 30;
    }

    public Transaction topUpTransaction(Account account, int amount) {

        Transaction transaction = new Transaction();
        account.setBalance(account.getBalance() - amount);
        account.setUpdatedDate(new Date());
        accountRepository.save(account);

        transaction.setAmount(amount);
        transaction.setSide("Debit");
        // transaction.setOtp(generateOTP());
        transaction.setStatus("Success");
        transaction.setTransactionCode(generateTransactionCode());
        transaction.setACIF(account.getACIF());
        transaction.setResponseCode(1);
        transaction.setTransactionDate(new Date());
        transaction.setRemark("Mobile Top Up from account number, " + account.getAccountNumber() + " ,  " + amount + " ETB");
        transactionRepository.save(transaction);

        return transaction;
    }

    private Transaction getPendingTransactionDetails(int acif, int otp) {
        List<Transaction> transaction = transactionRepository.findAll();
        Transaction t1 = new Transaction();
        for (Transaction transaction2 : transaction) {
            if (transaction2.getACIF() == acif && transaction2.getOtp() == otp) {
                t1.setStatus(transaction2.getStatus());
                t1.setRemark(transaction2.getRemark());
                t1.setOtp(transaction2.getOtp());
                t1.setTransactionCode(transaction2.getTransactionCode());
                t1.setTransactionDate(transaction2.getTransactionDate());
                t1.setAmount(transaction2.getAmount());
                t1.setRRN(transaction2.getRRN());
                t1.setACIF(transaction2.getACIF());
                t1.setResponseCode(transaction2.getResponseCode());
                t1.setSide(transaction2.getSide());

            }
        }
        return t1;
    }

    private Account getAccountDetails(int acif) {
        List<Account> account = accountRepository.findAll();
        Account a1 = new Account();
        for (Account acc : account) {
            if (acc.getACIF() == acif) {
                a1.setAccountStatus(acc.isAccountStatus());
                a1.setBalance(acc.getBalance());
                a1.setAccountNumber(acc.getAccountNumber());
                a1.setUpdatedDate(acc.getUpdatedDate());
                a1.setACIF(acc.getACIF());
            }
        }
        return a1;
    }

    public List<TransactionDTO> shortStatement(int accountId){
        List<Transaction> transactionList = transactionRepository.findAll();
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        for(Transaction tr: transactionList){
            if(tr.getACIF() == accountId){
                transaction.setTransactionCode(tr.getTransactionCode());
                transaction.setTransactionDate(tr.getTransactionDate());
                transaction.setRemark(tr.getRemark());
                transaction.setStatus(tr.getStatus());
                transaction.setAmount(tr.getAmount());
                transaction.setResponseCode(tr.getResponseCode());
                transaction.setSide(tr.getSide());
            }
            transactions.add(transaction);
        }
        if(!transactions.isEmpty()){
            return transactionDTO.shortStatementDTO(transactions.subList(transactions.size() -5, transactions.size()));
        }
        else{
            return null;
        }
    }
}

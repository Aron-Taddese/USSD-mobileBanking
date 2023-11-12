package com.gebeya.projectone.First.Project.services;

import com.gebeya.projectone.First.Project.beans.Account;
import com.gebeya.projectone.First.Project.beans.TopUp;
import com.gebeya.projectone.First.Project.repositories.AccountRepository;
import com.gebeya.projectone.First.Project.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Service
public class TopUpService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionService transactionService;

    public TopUp topUpMobileCard(int id, int amountToTopUp) {

        Account account = accountRepository.findById(id).get();
        TopUp topUp = new TopUp();
        if (account.getBalance() > amountToTopUp) {
            transactionService.topUpTransaction(account, amountToTopUp);
            WebClient webClient = WebClient.create();
            String url = "https://meinab.com/mtelecom/topup.php/"+ amountToTopUp;
           // String url = "http://192.168.1.43:9090/mtelecom/topup/" + amountToTopUp;
            return webClient.get().uri(url).retrieve().bodyToMono(TopUp.class).block();

        }
        topUp.setSenum("Not Insufficent Balance");
        return topUp;
    }
}

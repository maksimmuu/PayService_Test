package com.example.Service;

import com.example.Model.Account;
import com.example.Repository.AccountRepository;
import com.example.Repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final LogRepository logRepository;
    private final Logger logger;

    public TransferService(AccountRepository accountRepository, LogRepository logRepository, Logger logger) {
        this.accountRepository = accountRepository;
        this.logRepository = logRepository;
        this.logger = logger;
    }

    @Transactional
    public void transferMoney(int idSender, int idReceiver, int amount) {
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        int senderNewAmount = sender.getAmount() - amount;
        int receiverNewAmount = receiver.getAmount() + amount;

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);

        logger.info(sender.getName() + " с id=" + sender.getId() + " перевел пользователю "
                + receiver.getName() + " c id=" + receiver.getId()+ " " + amount + " $");

        logRepository.addLog(sender.getName() + " с id=" + sender.getId() + " перевел пользователю "
                + receiver.getName() + " c id=" + receiver.getId()+ " " + amount + " $");

    }


    public List<Account> getAllAccounts() {

        return accountRepository.findAllAccounts();
    }


    public void createAccount (String name, int amount){
        accountRepository.addAccount(name, amount);
        System.out.println("Аккаунт добавлен!");
    }

    public void deleteAccount (int id){
        accountRepository.deleteAccount(id);
        System.out.println("Аккаунт удален !");
    }
}

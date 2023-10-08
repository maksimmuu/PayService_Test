package com.example.Service;

import com.example.Model.Account;
import com.example.Model.Log;
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

        double senderNewAmount = 0;

        if (!sender.getCountry().equals(receiver.getCountry())) {
            senderNewAmount = sender.getAmount() - (amount + amount * 0.05);
            senderNewAmount=Math.round(senderNewAmount*100.0)/100.0;
        }else {
            senderNewAmount = sender.getAmount() - amount;
        }

        double receiverNewAmount = receiver.getAmount() + amount;

        if (sender.getAmount() > 0 && senderNewAmount >= 0) {

            accountRepository.changeAmount(idSender, senderNewAmount);
            accountRepository.changeAmount(idReceiver, receiverNewAmount);

            logger.info(sender.getName() + " с id=" + sender.getId() + " перевел пользователю "
                    + receiver.getName() + " c id=" + receiver.getId() + " " + amount + " $");

            logRepository.addLog(sender.getName() + " с id=" + sender.getId() + " перевел пользователю "
                    + receiver.getName() + " c id=" + receiver.getId() + " " + amount + " $", sender, amount);
        } else {
            System.out.println("Недостаточно средств для перевода");
            logRepository.addLog("Недостаточно средств: " + sender.getName() + " с id=" + sender.getId() + " пытался перевести пользователю "
                    + receiver.getName() + " c id=" + receiver.getId() + " " + amount + " $", sender, amount);
        }
    }

}

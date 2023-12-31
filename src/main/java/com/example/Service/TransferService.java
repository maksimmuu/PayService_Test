package com.example.Service;

import com.example.Model.Account;
import com.example.Repository.AccountPermissionRepository;
import com.example.Repository.AccountRepository;
import com.example.Repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.logging.Logger;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final AccountPermissionRepository accountPermissionRepository;
    private final LogRepository logRepository;
    private final Logger logger;

    public TransferService(AccountRepository accountRepository, AccountPermissionRepository accountPermissionRepository, LogRepository logRepository, Logger logger) {
        this.accountRepository = accountRepository;
        this.accountPermissionRepository = accountPermissionRepository;
        this.logRepository = logRepository;
        this.logger = logger;
    }

    @Transactional
    public void transferMoney(int idSender, int idReceiver, double amount) {
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        if (sender.getPermissionToTransfer() == null || !sender.getPermissionToTransfer().isBlockAccount()) {

            double senderNewAmount = 0;

            if (!sender.getCountry().equals(receiver.getCountry())) {
                senderNewAmount = sender.getAmount() - (amount + amount * 0.05);
                senderNewAmount = Math.round(senderNewAmount * 100.0) / 100.0;
            } else {
                senderNewAmount = sender.getAmount() - amount;
            }

            double receiverNewAmount = receiver.getAmount() + amount;

            if (sender.getAmount() > 0 && senderNewAmount >= 0) {

                accountRepository.changeAmount(idSender, senderNewAmount);
                accountRepository.changeAmount(idReceiver, receiverNewAmount);

                logger.info(sender.getName() + " с id=" + sender.getId() + " перевел пользователю "
                        + receiver.getName() + " c id=" + receiver.getId() + " " + amount + " $");

                logRepository.addLog("Успешный перевод " + sender.getName() + " с id=" + sender.getId() + " перевел пользователю "
                                + receiver.getName() + " c id=" + receiver.getId() + " " + amount + " $", sender, amount,
                        sender.getName(), receiver.getId(), receiver.getName());
            } else {
                logger.info("Недостаточно средств: " + sender.getName() + " с id=" + sender.getId() + " пытался перевести пользователю "
                        + receiver.getName() + " c id=" + receiver.getId() + " " + amount + " $");


                logRepository.addLog("Недостаточно средств: " + sender.getName() + " с id=" + sender.getId() + " пытался перевести пользователю "
                                + receiver.getName() + " c id=" + receiver.getId() + " " + amount + " $", sender, 0.0,
                        sender.getName(), receiver.getId(), receiver.getName());
            }
        }
        else {
            logger.info("Аккаунт с id=" + idSender + " заблокирован, перевод не будет произведен !");
            logRepository.addLog("Аккаунт с id=" + idSender + " заблокирован, перевод не будет произведен"
                    , sender, 0.0, sender.getName(), receiver.getId(), receiver.getName());

        }
    }

}

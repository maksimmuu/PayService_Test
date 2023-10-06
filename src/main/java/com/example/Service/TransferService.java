package com.example.Service;

import com.example.Model.Account;
import com.example.Repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class TransferService {

    private AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(int idSender, int idReceiver, int amount) throws SQLException {
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        int senderNewAmount = sender.getAmount() - amount;
        int receiverNewAmount = receiver.getAmount() + amount;

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);

    }


    public List<Account> getAllAccounts() throws SQLException {
       return accountRepository.findAllAccounts();
    }
}

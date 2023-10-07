package com.example.payService;
import com.example.Model.Account;
import com.example.Repository.AccountRepository;
import com.example.Repository.LogRepository;
import com.example.Service.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferMoneyUnitTests {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    LogRepository logRepository;

    @Mock
    Logger logger;

    @InjectMocks
    TransferService transferService;

    @Test
    public void transferMoneyHappyFlow ()  {

        Account sender = new Account();
        sender.setId(2);
        sender.setAmount(1000);

        Account receiver = new Account();
        receiver.setId(4);
        receiver.setAmount(500);

        given(accountRepository.findAccountById(sender.getId())).willReturn(sender);

        given(accountRepository.findAccountById(receiver.getId())).willReturn(receiver);

        transferService.transferMoney(sender.getId(), receiver.getId(), 200);

        verify(accountRepository).changeAmount(2, 800);
        verify(accountRepository).changeAmount(4, 700);


    }

}

package com.example.Controller;

import com.example.Model.Account;
import com.example.Model.TransferRequest;
import com.example.Service.AccountManageService;
import com.example.Service.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final TransferService transferService;
    private final AccountManageService accountManageService;

    public AccountController(TransferService transferService, AccountManageService accountManageService) {
        this.transferService = transferService;
        this.accountManageService = accountManageService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request){
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return accountManageService.getAllAccounts();
    }

}

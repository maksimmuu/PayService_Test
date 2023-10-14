package com.example.Service;

import com.example.Model.Account;
import com.example.Model.Log;
import com.example.Repository.AccountPermissionRepository;
import com.example.Repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountManageService {

    private AccountRepository accountRepository;
    private AccountPermissionRepository accountPermissionRepository;


    public AccountManageService(AccountRepository accountRepository, AccountPermissionRepository accountPermissionRepository) {

        this.accountRepository = accountRepository;
        this.accountPermissionRepository = accountPermissionRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    public List<Log> getLogBySenderId (int sender_id){
        return accountRepository.findLogsBySenderId(sender_id);
    }


    public void createAccount (String name, double amount, String country){
        accountRepository.addAccount(name, amount, country);
        System.out.println("Аккаунт добавлен!");
    }

    public void deleteAccount (int id){
        accountRepository.deleteAccount(id);
        System.out.println("Аккаунт удален !");
    }

    public double sumSendAllById(int id){
       return accountRepository.sumSendById(id);
    }

    public void blockAccount (int id){
        accountPermissionRepository.setBlock(id);
        System.out.println("Аккаунт с id=" + id + " заблокирован !");
    }

    public void unblockAccount (int id){
        accountPermissionRepository.deleteBlock(id);
        System.out.println("Аккаунт с id=" + id + " разблокирован !");
    }

}

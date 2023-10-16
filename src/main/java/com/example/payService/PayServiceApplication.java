package com.example.payService;

import com.example.Config.SpringConfig;
import com.example.Service.AccountManageService;
import com.example.Service.TransferService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@SpringBootApplication
public class PayServiceApplication {

	public static void main(String[] args)  {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		TransferService transferService = context.getBean(TransferService.class);

		AccountManageService accountManageService = context.getBean(AccountManageService.class);

		accountManageService.createAccount("Paul", 300.0, "Spain"); // создание аккаунта
		accountManageService.blockAccount(10); // блокировка аккаунта по id
		accountManageService.unblockAccount(10); // разблокировка аккаунта по id
		accountManageService.getAllAccounts(); // вывод всех аккаунтов
		accountManageService.sumSendAllById(2); // общая сумма всех переводов по id
		accountManageService.deleteAccount(9); // удаление аккаунта
		accountManageService.getLogBySenderId(1); // все логи переводов по id

		transferService.transferMoney(1,2,100.0); // перевод, информация о перевод сохраняется в БД

	}

}

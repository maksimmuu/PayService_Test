package com.example.payService;

import com.example.Config.SpringConfig;
import com.example.Model.Log;
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


		//transferService.transferMoney(1,2,65);

		for (Log l: accountManageService.getLogBySenderId(1)) {
			System.out.println(l);
		}

		accountManageService.deleteAccount(8);
		accountManageService.deleteAccount(9);
		System.out.println(accountManageService.getAllAccounts());

	}

}

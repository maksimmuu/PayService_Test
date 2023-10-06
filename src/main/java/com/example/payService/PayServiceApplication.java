package com.example.payService;

import com.example.Config.SpringConfig;
import com.example.Model.Account;
import com.example.Repository.AccountRepository;
import com.example.Service.TransferService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class PayServiceApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);



		TransferService transferService = context.getBean(TransferService.class);
//		System.out.println(transferService.getAllAccounts());
//
//
		System.out.println(transferService.getAllAccounts());

		transferService.transferMoney(1,3,340);
		transferService.transferMoney(2,8,10);
		transferService.transferMoney(3,1,880);
		transferService.transferMoney(4,4,140);




	}

}

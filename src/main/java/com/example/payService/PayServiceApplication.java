package com.example.payService;

import com.example.Config.SpringConfig;
import com.example.Service.TransferService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@SpringBootApplication
public class PayServiceApplication {

	public static void main(String[] args)  {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		TransferService transferService = context.getBean(TransferService.class);

		System.out.println(transferService.getAllAccounts());

		transferService.transferMoney(5,1,0);

	}

}

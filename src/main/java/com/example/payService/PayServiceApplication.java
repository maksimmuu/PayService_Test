package com.example.payService;

import com.example.Config.SpringConfig;
import com.example.Model.Account;
import com.example.Repository.AccountRepository;
import com.example.Service.TransferService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PayServiceApplication {

	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		TransferService transferService = context.getBean(TransferService.class);
		System.out.println(transferService.getAllAccounts());

//		AccountRepository accountRepository = context.getBean(AccountRepository.class);
//		List <Account> lst = accountRepository.findAllAccounts();
//
//		System.out.println(lst);


	}

}

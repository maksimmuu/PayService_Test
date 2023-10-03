package com.example.payService;

import com.example.Repository.AccountRepository;
import com.example.Service.TransferService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

@SpringBootApplication
public class PayServiceApplication {

	public static void main(String[] args) {
		TransferService transferService = new TransferService(new AccountRepository(new JdbcTemplate()));
		transferService.transferMoney(1,2,new BigDecimal(100));

	}

}

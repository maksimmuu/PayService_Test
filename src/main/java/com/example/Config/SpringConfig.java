package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example")
public class SpringConfig {

//    @Bean
//    public DataSource dataSource (){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgres.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/account_db");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("13libuge");
//
//        return dataSource;
//    }
}

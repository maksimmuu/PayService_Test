package com.example.Config;

import com.example.Model.Account;
import com.example.Model.Log;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@ComponentScan("com.example")
public class SpringConfig {

    @Bean
    public DataSource dataSource (){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/account_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("13libuge");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate (){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public org.hibernate.cfg.Configuration configuration () {
        return new org.hibernate.cfg.Configuration().addAnnotatedClass(Account.class).addAnnotatedClass(Log.class);
    }


    @Bean
    public Logger logger (){
        return Logger.getGlobal();
    }
}

package com.example.Repository;

//import com.example.AccountRowMapper;
import com.example.AccountRowMapper;
import com.example.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;


    @Autowired
    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public Account findAccountById (int id) {
        return jdbc.query("SELECT * FROM account where id = ?", new Object[]{id}, new AccountRowMapper())
                .stream().findAny().orElse(null);
    }

    public void changeAmount (int id, int amount)  {
        jdbc.update("UPDATE account SET amount = ? WHERE id = ?", amount, id);
    }

    public List<Account> findAllAccounts() {
        return jdbc.query("SELECT * FROM account", new AccountRowMapper());
        }

}

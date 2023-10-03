package com.example;

import com.example.Model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account a = new Account();
        a.setId(rs.getLong("id"));
        a.setName(rs.getString("name"));
        a.setAmount(rs.getBigDecimal("amount"));

        return a;
    }
}

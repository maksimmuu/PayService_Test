package com.example.Repository;

//import com.example.AccountRowMapper;
import com.example.Model.Account;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    //   private final JdbcTemplate jdbc;

//    public AccountRepository(JdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }
//

    private static String URL = "jdbc:postgresql://localhost:5432/account_db";
    private static String USER = "postgres";
    private static String PASSWORD = "13libuge";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Account findAccountById (int id) throws SQLException {
        Account account = new Account();

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM account WHERE id =" + id;
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            account.setName(resultSet.getString("name"));
            account.setId(resultSet.getInt("id"));
            account.setAmount(resultSet.getInt("amount"));
        }

        return account;
    }

    public void changeAmount (int id, int amount) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE account SET amount =" + amount + " WHERE id =" + id;
        statement.executeUpdate(sql);

    }

    public List<Account> findAllAccounts() throws SQLException {
        List <Account> lst = new ArrayList<>();

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM account";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Account account = new Account();

            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setAmount(resultSet.getInt("amount"));

            lst.add(account);
        }

        return lst;
    }
}

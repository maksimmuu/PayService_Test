package com.example.Repository;

//import com.example.AccountRowMapper;
import com.example.AccountRowMapper;
import com.example.Model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;
    private final Configuration configuration;


    public AccountRepository(JdbcTemplate jdbc, SessionFactory sessionFactory, Configuration configuration) {
        this.jdbc = jdbc;
        this.configuration = configuration;

    }


    public List<Account> findAllAccounts() {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List <Account> allAccounts = session.createQuery("from Account").getResultList();

        session.getTransaction().commit();
        //return jdbc.query("SELECT * FROM account", new AccountRowMapper());
        return allAccounts;
        }

    public Account findAccountById (int id) {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account = session.get(Account.class, id);

        session.getTransaction().commit();

        return account;
    }

    public void changeAmount (int id, int amount)  {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account = session.get(Account.class, id);
        account.setAmount(amount);

        session.getTransaction().commit();
    }

    public void addAccount (String name, int amount){
        Account account = new Account(name, amount);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(account);

        session.getTransaction().commit();

    }

    public void deleteAccount (int id){
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account_del = session.get(Account.class, id);
        session.delete(account_del);

        session.getTransaction().commit();
    }

}

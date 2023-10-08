package com.example.Repository;
import com.example.Model.Account;
import com.example.Model.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    private final Configuration configuration;


    public AccountRepository(SessionFactory sessionFactory, Configuration configuration) {

        this.configuration = configuration;

    }


    public List<Account> findAllAccounts() {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List <Account> allAccounts = session.createQuery("from Account").getResultList();

        session.getTransaction().commit();
        session.close();
        return allAccounts;
        }

    public Account findAccountById (int id) {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

       Account account = session.get(Account.class, id);

        session.getTransaction().commit();
        session.close();

        return account;
    }

    public List <Log> findLogsBySenderId (int sender_id){
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account sender = session.get(Account.class, sender_id);
        session.close();
        return sender.getLogs();
    }

    public void changeAmount (int id, double amount)  {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account = session.get(Account.class, id);
        account.setAmount(amount);

        session.getTransaction().commit();
        session.close();
    }

    public void addAccount (String name, double amount, String country){
        Account account = new Account();
        account.setName(name);
        account.setAmount(amount);
        account.setCountry(country);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(account);

        session.getTransaction().commit();
        session.close();

    }

    public void deleteAccount (int id){
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account_del = session.get(Account.class, id);
        session.delete(account_del);

        session.getTransaction().commit();
        session.close();
    }

}

package com.example.Repository;
import com.example.Model.Account;
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

    public void changeAmount (int id, int amount)  {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account = session.get(Account.class, id);
        account.setAmount(amount);

        session.getTransaction().commit();
        session.close();
    }

    public void addAccount (String name, int amount){
        Account account = new Account(name, amount);

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

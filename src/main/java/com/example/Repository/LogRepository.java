package com.example.Repository;

import com.example.Model.Account;
import com.example.Model.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

@Repository
public class LogRepository {

    private Configuration configuration;

    public LogRepository(Configuration configuration) {
        this.configuration = configuration;
    }

    public void addLog (String logMessage, Account sender, double amount,
                        String senderName, int receiverId, String receiverName){

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Timestamp timestamp = new Timestamp(new Date().getTime());
        Log log = new Log(sender, senderName, receiverId, receiverName, amount, logMessage, timestamp);

        session.beginTransaction();

        session.save(log);

        session.getTransaction().commit();
        session.close();

    }
}

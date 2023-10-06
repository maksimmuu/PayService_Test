package com.example.Repository;

import com.example.Model.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepository {

    private final Configuration configuration;

    public LogRepository(Configuration configuration) {
        this.configuration = configuration;
    }

    public void addLog (String logMessage){
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Log log = new Log();
        log.setLogMessage(logMessage);

        session.beginTransaction();

        session.save(log);

        session.getTransaction().commit();

    }
}

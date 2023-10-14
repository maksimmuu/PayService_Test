package com.example.Repository;

import com.example.Model.Account;
import com.example.Model.PermissionToTransfer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class AccountPermissionRepository {

    private Configuration configuration;

    public AccountPermissionRepository(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setBlock (int id){
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account = session.get(Account.class, id);

            if(account.getPermissionToTransfer()==null){
        PermissionToTransfer permissionToTransfer = new PermissionToTransfer(account, true);
        account.setPermissionToTransfer(permissionToTransfer);
        session.save(permissionToTransfer);}
            else {
                account.getPermissionToTransfer().setBlockAccount(true);
            }

        session.getTransaction().commit();
        session.close();
    }

    public void deleteBlock (int id){
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Account account = session.get(Account.class, id);

        if (account.getPermissionToTransfer()==null){
        PermissionToTransfer permissionToTransfer = new PermissionToTransfer(account, false);
        account.setPermissionToTransfer(permissionToTransfer);
        session.save(permissionToTransfer);}
        else {
            account.getPermissionToTransfer().setBlockAccount(false);
        }

        session.getTransaction().commit();
        session.close();
    }
}

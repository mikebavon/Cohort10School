package com.cohort10.bean;

import com.cohort10.model.Auth;
import com.cohort10.model.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuthBean implements AuthBeanI {

    @PersistenceContext
    EntityManager em;

    public User login(Auth auth) throws Exception{

        if (auth.getUsername() == null || auth.getPassword() == null)
            throw new Exception("Invalid password or username");

        List<Auth> auths = em.createQuery("FROM Auth a WHERE a.username=:usrName " +
            "and a.password=:pwd", Auth.class)
            .setParameter("usrName", auth.getUsername())
            .setParameter("pwd", auth.getPassword())
            .getResultList();

        if (auths == null || auths.isEmpty() || auths.get(0) == null)
            throw new Exception("Invalid username or password");

        return auths.get(0).getUser();

    }
}

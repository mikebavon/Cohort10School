package com.cohort10.bean;

import com.cohort10.model.Auth;
import com.cohort10.model.User;
import org.apache.commons.codec.digest.DigestUtils;

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

        auths.get(0).getUser().setBearerToken(DigestUtils
            .md5Hex(auth.getUsername() + " SALT=CH10 " + auth.getPassword()));

        return auths.get(0).getUser();

    }

    public boolean authMd5(String md5Hash){

        if (md5Hash == null)
            return false;

        List<Auth> auths = em.createQuery("FROM Auth a", Auth.class)
            .getResultList();

        if (auths == null || auths.isEmpty())
            return false;

        boolean authenticated = false;
        for (Auth auth : auths) {
            if (DigestUtils.md5Hex(auth.getUsername() + " SALT=CH10 " + auth.getPassword()).equals(md5Hash)) {
                authenticated = true;
                break;
            }
        }

        return  authenticated;

    }
}

package com.cohort10.controllers;

import com.cohort10.model.Auth;
import com.cohort10.model.Status;
import com.cohort10.model.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class UserBean implements UserBeanI{

    @PersistenceContext
    EntityManager em;

    public User register(User user) throws Exception{
        if (user == null)
            throw new Exception("Invalid details");

        if (user.getEmail() == null)
            throw new Exception("Email is required");

        Auth auth = new Auth();
        auth.setUsername(user.getEmail());
        if (user.getPassword() == null || user.getConfirmPassword() == null
                || !user.getPassword().equals(user.getConfirmPassword()))
            throw new Exception("Password & confirm password is required and must match");

        auth.setUsername(user.getEmail());
        auth.setPassword(user.getPassword());
        auth.setConfirmPassword(user.getConfirmPassword());
        auth.setStatus(Status.ACTIVE);

        user.addAuth(auth);

        return em.merge(user);

    }
}

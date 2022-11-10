package com.cohort10.bean;

import com.cohort10.model.Auth;
import com.cohort10.model.Status;
import com.cohort10.model.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserBean implements UserBeanI{

    @PersistenceContext
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
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

        //if id present the record will be updated........
        // user.getId() != null merge will update record rather than insert....

        return em.merge(user);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(User userUpdate){

        User user = em.find(User.class, userUpdate.getId());

        user.setEmail(userUpdate.getEmail());
        user.setProfile(userUpdate.getProfile());

        em.merge(user);

    }
}

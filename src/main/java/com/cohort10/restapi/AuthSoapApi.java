package com.cohort10.restapi;

import com.cohort10.bean.AuthBeanI;
import com.cohort10.model.User;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService
public class AuthSoapApi {

    @EJB
    AuthBeanI authBean;

    public User login(String username, String password) {
        return new User(); //authBean.login(new Auth());
    }
}

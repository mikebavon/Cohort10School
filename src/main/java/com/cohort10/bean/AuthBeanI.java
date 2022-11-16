package com.cohort10.bean;

import com.cohort10.model.Auth;
import com.cohort10.model.User;

public interface AuthBeanI {

    User login(Auth auth) throws Exception;

    boolean authMd5(String md5Hash);

}

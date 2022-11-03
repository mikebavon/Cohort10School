package com.cohort10.controllers;

import com.cohort10.model.User;

public interface AuthBeanI {

    User login(String username, String password);

}

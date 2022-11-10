package com.cohort10.bean;

import com.cohort10.model.User;

public interface UserBeanI {
    User register(User user) throws Exception;

    void update(User userUpdate);

}

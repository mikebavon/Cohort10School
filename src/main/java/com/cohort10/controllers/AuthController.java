package com.cohort10.controllers;

import com.cohort10.model.User;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Stateless
@Remote
public class AuthController {

    @Resource(lookup = "java:jboss/datasources/School")
    DataSource dataSource;

    public User login(String username, String password) {

        User user = null;

        try {
            Connection connection = dataSource.getConnection();
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from users where username='" + username + "' and " +
                    "password='" + password + "'");
            while (result.next()) {
                user = new User();
                user.setId((long) result.getInt("id"));
                user.setUsername(result.getString("username"));
            }

        }catch (Exception ex) {
            System.out.println("Log In Error: " + ex.getMessage());
        }

        return user;

    }
}

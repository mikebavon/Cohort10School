package com.cohort10.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class DatabaseConnection {
  
    private static Connection con = null;
  
    static
    {
        String url = "jdbc:mysql:// localhost:3306/org";
        String user = "root";
        String pass = "root";

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

}
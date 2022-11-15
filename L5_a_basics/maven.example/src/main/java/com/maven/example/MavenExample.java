package com.maven.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MavenExample {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample" , "root", "");

        Statement statement = connection.createStatement();
        statement.execute("create  table  demo_user (" +
                " id int(10) primary  key , " +
                " user_roles varchar (100) DEFAULT null , " +
                " name varchar (50) DEFAULT null )");
    }
}

package com.l5.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Maven {

    /**
     *
     * MYSQL -- DB
     *      ---> relation DB
     *
     *  JDBC - Java Database Connectivity -- Protocol
     *
     *  browser
     *      HTTP - :80
     *      HTTPS -- :443
     *
     *      FTP - File Transfer Protocol
     *      SMTP - Simple Mail Transfer Protocol
     *          --> Notification Engine --- Project 2
     *          (Rest Api call) -- Kafka Consumer
     *
     *
     *
     *  userinfo {
     *      String name;
     *     String roles;
     *     int id;
     *  }
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) throws SQLException {
            //jdbc:subprotocol:subname
        // mysql - 3306
        // postgres - 5432
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample" , "root", "");

        Statement statement = connection.createStatement();
        statement.execute("create  table  user (" +
                " id int(10) primary  key , " +
                " user_roles varchar (100) DEFAULT null , " +
                " name varchar (50) DEFAULT null )");

    }

    /**
     *
     * analogy
     * String -- varchar or text
     * List / map --- JSON / JSONB
     *  Integer -- int
     *  Boolean -- bol or tiny int(1)
     *
     */


    /**
     * Adding more components
     * Configuring things manually
     *
     * -- not scalable
     * -- its more error prone
     * -- one dependency -- requiring another dependency
     *  - Kafka (cover in later part of the course) -- zookeeper libraries while running (old)
     *
     *  - Spring Data JPA
     *  - redis
     *  - spring security (older implementaiton vs springboot 2.7.x implementaiton)
     *  - Kafka
     *
     *
     *
     */


    /**
     * We are able to resolve the dependency
     *
     *      mysql jar would be present
     *           (local)
     *           (remote)
     *           (central)
     *
     *              ---> if it has been present in the local
     *                      --> yes
     *                      --> no
     *                     -------------------------> remote repository
     *                <--                                  -- yes
     *                                                  -- no
     *                                                   ------------------------> central repository
     *                                                                              - yes
     *
     *                  <--------------------------------------------------------
     *
     *      *  Lifecycle of maven --
     *      *                       clean , compile , test , package , install, deploy
     *      *      Done
     *
     *
     */

}

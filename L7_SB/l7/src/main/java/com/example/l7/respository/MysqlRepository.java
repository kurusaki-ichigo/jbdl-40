package com.example.l7.respository;

import com.example.l7.entities.UserEntity;
import com.example.l7.utils.AppConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

/**
 *  private Long id;
 *     private String name;
 *     private String email;
 *
 */
@Slf4j
//@Repository(value = AppConstants.MYSQL_REPOSITORY)
public class MysqlRepository implements IRepository, InitializingBean {

    private Connection connection;

    private static final String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS `user`  (\n "+
            "`id` int(10) NOT NULL AUTO_INCREMENT,\n " +
            "  `name` varchar(50) DEFAULT NULL,\n" +
            "  `email` varchar(50) NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ")  " ;

    private static final String INSERT_INTO_USER = " INSERT INTO `user` (`name`, `email`) " +
            "value  (?,?) ";

    /*

        insert into user(`name`) value (--- )
     insert into user(`name`) value (+ user_input + )
     user_input = `nyc`); select * from user where id in (0,1000
      insert into user(`name`) value (`nyc`); select * from user where id in (0,1000)

      PreparedStatement vs Statement


     */
    @Override
    @SneakyThrows
    public UserEntity save(UserEntity entity) {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USER);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getEmail());
        int count = preparedStatement.executeUpdate();
        log.info(" saving user {} ", count);
        return entity;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mysql_user" , "root", "");
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
        log.info("User created");
    }
}

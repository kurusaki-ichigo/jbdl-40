package com.example.l7.respository;

import com.example.l7.entities.UserEntity;
import com.example.l7.utils.AppConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

//@Repository(AppConstants.H2_REPOSITORY)
@Slf4j
public class H2Repository implements IRepository , InitializingBean {

    private Connection connection;

    private static final String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS `user`  (\n "+
            "`id` integer NOT NULL AUTO_INCREMENT,\n " +
            "  `name` varchar(50) DEFAULT NULL,\n" +
            "  `email` varchar(50) NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ")  " ;

    private static final String INSERT_INTO_USER = " INSERT INTO `user` (`name`, `email`) values  (?,?) ";


    public UserEntity findById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(" select * from user where id = " + id);
        UserEntity entity = null;
       if (resultSet.next()){
           entity = new UserEntity();
           entity.setId(resultSet.getLong("id"));
           entity.setName(resultSet.getString("name"));
           entity.setEmail(resultSet.getString("email"));
       }
       return entity;
    }

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
        connection = DriverManager.getConnection("jdbc:h2:mem:test" , "root", "");
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
        log.info("User created");
    }
}

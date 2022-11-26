package com.example.l7.respository;

import com.example.l7.entities.UserEntity;

import java.util.Optional;

public interface IRepository {


    UserEntity save(UserEntity entity);

    Optional<UserEntity> findByEmail(String email);

}

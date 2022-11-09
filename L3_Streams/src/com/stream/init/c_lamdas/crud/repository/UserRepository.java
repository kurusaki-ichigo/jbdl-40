package com.stream.init.c_lamdas.crud.repository;

import com.stream.init.c_lamdas.crud.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UserRepository {


    Map<String, User> users = new HashMap<>();


    /**
     * persist a user
     */

    public void persist(User user){
        users.put(user.getName(),user);
    }

    /**
     *
     * fetch a user by name
     */
    public Optional<User> findUserByName(String name){
        if(Objects.isNull(name) || "".equalsIgnoreCase(name)){
            return Optional.empty();
        }
        User user = users.get(name);
        return Optional.ofNullable(user);
    }



}

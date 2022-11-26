package com.example.l7.service;

import com.example.l7.entities.UserEntity;
import com.example.l7.exception.UserExistsException;
import com.example.l7.requests.CreateUserInfoRequest;
import com.example.l7.respository.IRepository;
import com.example.l7.respository.UserRepository;
import com.example.l7.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


//    @Autowired
//    private MysqlRepository mysqlRepository;

    /**
     *
     *  name resolution
     *      -- beans
     *
     *      (name , bean)
     *
     */
    @Autowired
    UserRepository userRepository;

    public UserEntity createNewUser(CreateUserInfoRequest userInfoRequest){
        Optional<UserEntity> exiting = findByEmail(userInfoRequest.getEmail());
        if(exiting.isPresent()){
            throw new UserExistsException("User already exists");
        }

        UserEntity newUser = userInfoRequest.toUser();
        return saveOrUpdate(newUser);
    }

    public Optional<UserEntity> findByEmail(String email){
       return userRepository.findByEmail(email);
    }

    public UserEntity saveOrUpdate(UserEntity user){
        return userRepository.save(user);
    }
}

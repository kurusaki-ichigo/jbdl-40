package com.example.user.user.service;

import com.example.user.user.entity.UserEntity;
import com.example.user.user.repository.UserRepository;
import com.example.user.user.requsts.CreateNewUserRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    UserRepository userRepository;


    @SneakyThrows
    public UserEntity createOrFetchExistingUser(CreateNewUserRequest request){
        UserEntity newUser = request.toUser();
        Thread.sleep(10000);
        Optional<UserEntity> existingUser = userRepository.findByEmail(newUser.getEmail());
        return existingUser.orElseGet(() -> saveOrUpdate(newUser));
    }


    public UserEntity saveOrUpdate(UserEntity user){
        return userRepository.save(user);
    }
}

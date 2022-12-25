package com.example.ewallet.user.service;

import com.example.ewallet.user.entity.UserInfo;
import com.example.ewallet.user.exception.DuplicateUserException;
import com.example.ewallet.user.exception.UserNotFoundException;
import com.example.ewallet.user.models.StatusCode;
import com.example.ewallet.user.repository.UserInfoRepository;
import com.example.ewallet.user.request.CreateNewUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {


    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public static final String NEW_USER_CREATED = "NEW_USER_CREATED";

    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public UserInfo createNewUser(CreateNewUserRequest userRequest){
        UserInfo transientUserInfo = userRequest.toUserInfo();
        /**
         * fetch existing user
         */
        Optional<UserInfo> existingUser = userInfoRepository.findByEmail(transientUserInfo.getEmail());
        if(existingUser.isPresent()){
            throw new DuplicateUserException(StatusCode.DUPLICATE_USER);
        }
        return saveOrUpdate(transientUserInfo);
    }



    public UserInfo fetchUserByUUID(UUID userId){
        return userInfoRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(StatusCode.USER_NOT_FOUND));
    }

    @SneakyThrows
    public void notifyNewUserCreation(UserInfo userInfo){
        log.info(" sending message {} -- {} ", NEW_USER_CREATED, userInfo);
        kafkaTemplate.send(NEW_USER_CREATED, objectMapper.writeValueAsString(userInfo));
    }


    public UserInfo saveOrUpdate(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }

}

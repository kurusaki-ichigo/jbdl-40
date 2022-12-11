package com.example.user.user.controller;

import com.example.user.user.entity.UserEntity;
import com.example.user.user.requsts.CreateNewUserRequest;
import com.example.user.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> createNewUser(@RequestBody CreateNewUserRequest request){
        return new ResponseEntity<>(userService.createOrFetchExistingUser(request), HttpStatus.CREATED);
    }


}

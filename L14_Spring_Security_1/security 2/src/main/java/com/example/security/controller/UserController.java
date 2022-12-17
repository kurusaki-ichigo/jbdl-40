package com.example.security.controller;

import com.example.security.entity.UserInfo;
import com.example.security.request.CreateNewUserRequest;
import com.example.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> createANewUser(@RequestBody CreateNewUserRequest createUserRequestDto){
        log.info(" request received {} ", createUserRequestDto);
        return new ResponseEntity<>(userService.createNewUser(createUserRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{emailId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetails> fetchUserInfo(@PathVariable(name = "emailId") String emailId){
        log.info(" request received {} ", emailId);
        return new ResponseEntity<>(userService.loadUserByUsername(emailId), HttpStatus.CREATED);
    }
}

package com.example.ewallet.user.controller;

import com.example.ewallet.user.entity.UserInfo;
import com.example.ewallet.user.request.CreateNewUserRequest;
import com.example.ewallet.user.service.UserService;
import com.example.ewallet.user.utils.ResponseGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OnboardingController {

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    UserService userService;


    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBook(@Valid @RequestBody CreateNewUserRequest request) {
        UserInfo newUser = userService.createNewUser(request);
        userService.notifyNewUserCreation(newUser);
        return responseGenerator.generateResponse(newUser, HttpStatus.CREATED);
    }


    @GetMapping(value = "/user/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchUserDetails(@PathVariable(value = "uuid") String userId) {
        UUID userUuid = UUID.fromString(userId);
        return responseGenerator.generateResponse(userService.fetchUserByUUID(userUuid), HttpStatus.OK);
    }

}

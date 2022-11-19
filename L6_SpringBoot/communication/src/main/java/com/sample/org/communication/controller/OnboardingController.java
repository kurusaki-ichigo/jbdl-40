package com.sample.org.communication.controller;

import com.sample.org.communication.requests.CreateUserInfoRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class OnboardingController {


    @PostMapping("/user")
    public ResponseEntity<CreateUserInfoRequest> createAUser(
            @Valid
            @RequestBody CreateUserInfoRequest createUserInfoRequest){
        return new ResponseEntity<>(createUserInfoRequest, HttpStatus.OK);
    }



}

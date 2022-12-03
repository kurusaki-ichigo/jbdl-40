package com.example.L8.controller;

import com.example.L8.requests.CreateNewBookRequest;
import com.example.L8.requests.CreateNewUserRequest;
import com.example.L8.service.UserService;
import com.example.L8.utils.ResponseGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    ResponseGenerator responseGenerator;


    @Autowired
    UserService userService;

    /**
     *
     *      What is the request flow
     *
     *              embedded server
     *
     *                  (tomcat is being embedded in SPringboot Application)
     *                  (dispatcherServlet) --> thread started in TOMCAT
     *                                          ----> REquestMapping Handler Mapping
     *                                                                      --> which method to call
     *
     *              who handles this conversion and what library is being used
     *                                                      (JAckson)
     *  {
     *      Json format             ------> converted to JAVA OBJECT
     *  }
     *
     *  JSON                        <-------                 (Java Object)
     *                                                    (which library -- ? )
     *                                                    conversion of java to json
     *                                                         (JAckson)
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewUser(@Valid @RequestBody CreateNewUserRequest request){
        return responseGenerator.generateResponse(userService.createNewUser(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findUser(@PathVariable("uuid") String uuid){
        return responseGenerator.generateResponse(userService.findById(uuid), HttpStatus.OK);
    }

}

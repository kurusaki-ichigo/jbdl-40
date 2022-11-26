package com.example.l7.controller;

import com.example.l7.entities.UserEntity;
import com.example.l7.requests.CreateUserInfoRequest;
import com.example.l7.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class OnboardingController {

    @Autowired
    private UserService userService;


    /**
     * @Component / directly or indirectly
     */

    /**
     *      MVC architecture
     *
     *
     *     Model
     *     View
     *     Controller
     *
     *          SOLID
     *              - S - Single Reponsibility
     *              Classes are open for extention but closed for modification
     *
     *
     *      (Interacts with FE)             (has business logic)                        (INteracts with DB)
     *      Controller  -----                Service                                      Repository
     *
     *
     *  Onboard a user
     *                                  (busines logic)
     *                                          --> check if user exists / not
     *                                          if not
     *                                              create one
     *                                           or else
     *                                              return exiting
     *
     *
     *
     *  IOC , Dependecy Injection
     *
     *      IOC
     *              -- Spring identifying the beans
     *                      annotated with @COmponent directly or indeirectly
     *                      a new bean and manages its lifcycle
     *
     *
     *
     * @param createUserInfoRequest
     * @return
     */

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> publicCreateNewUser(@Valid @RequestBody CreateUserInfoRequest createUserInfoRequest){
        return new ResponseEntity<>(userService.createNewUser(createUserInfoRequest), HttpStatus.CREATED);
    }


}

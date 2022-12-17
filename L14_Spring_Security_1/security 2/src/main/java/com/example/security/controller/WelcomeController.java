package com.example.security.controller;

import com.example.security.entity.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class WelcomeController {



    /**
     *
     * Application Context
     *  ---> IOC
     *  inversion of Control ---> Layman terms
     *                                      ---> You need not need to take care of managing a bean lifecycle
     *                                      spring takes care of it .
     *
     *  Spring Takes care of it    ??
     *      Dependency Injection
     *      How does this take place ---- ?
     *      Class with component ---> what
     *                  Context -- container where every bean instance is present
     *
     *
     *      * Application Context -- kind of an interface where to the spring container
     *
     *
     *  Security Context
     *     ---> User
     *
     * @return
     */

    @Autowired
    BeanFactory beanFactory;


    @Secured("ROLE_MERCHANT")
    @GetMapping(value = "/merchant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeMerchant(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(" received user {}", user);
        return new ResponseEntity<>(String.format("Hey I am merchant ! with following details %s ", user), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeCustomer(){
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(" received user {}", user);
        return new ResponseEntity<>(String.format("Hey I am customer ! with following details %s ", user), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('CUSTOMER') OR hasRole('MERCHANT')")
    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeDeliveryPartner(){
        return new ResponseEntity<>("Hey I am accessible  by everyone ! ", HttpStatus.OK);
    }



}

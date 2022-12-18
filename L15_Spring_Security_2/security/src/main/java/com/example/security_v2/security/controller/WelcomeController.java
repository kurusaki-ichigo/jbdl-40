package com.example.security_v2.security.controller;

import com.example.security_v2.security.SecurityApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class WelcomeController {


    /**
     * would this api be secured
     *  --> if yes ? why
     *
     *  if no ? why ?
     *
     *  -- {@link SecurityApplication} {@link EnableMethodSecurity} - and {@link  EnableMethodSecurity#securedEnabled()} = true
     * @return
     */
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
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(" received user {}", user);
        return new ResponseEntity<>(String.format("Hey I am customer ! with following details %s ", user), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('CUSTOMER') OR hasRole('MERCHANT')")
    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeDeliveryPartner(){
        return new ResponseEntity<>("Hey I am accessible  by everyone ! ", HttpStatus.OK);
    }



}

package com.example.order.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeDeliveryPartner(){
        return new ResponseEntity<>("Hey I am accessible  by everyone ! ", HttpStatus.OK);
    }


}

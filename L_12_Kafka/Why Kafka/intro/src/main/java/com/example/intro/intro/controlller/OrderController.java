package com.example.intro.intro.controlller;

import com.example.intro.intro.entity.Orders;
import com.example.intro.intro.request.CreateOrdersRequest;
import com.example.intro.intro.service.OrderService;
import jakarta.validation.Valid;
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
public class OrderController {


    @Autowired
    OrderService orderService;



    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> createNewUser(@Valid @RequestBody CreateOrdersRequest request){
        return new ResponseEntity<>(orderService.createOrderV2(request), HttpStatus.CREATED);
    }



}

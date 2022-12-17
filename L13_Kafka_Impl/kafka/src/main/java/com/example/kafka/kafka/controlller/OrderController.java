package com.example.kafka.kafka.controlller;

import com.example.kafka.kafka.entity.Orders;
import com.example.kafka.kafka.request.CreateOrdersRequest;
import com.example.kafka.kafka.service.OrderService;
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
    public ResponseEntity<Orders> createNewUser(@RequestBody CreateOrdersRequest request){
        Orders orderV2 = orderService.createOrderV2(request);
        orderService.sendMessageToKafka(orderV2.getOrderId());
        return new ResponseEntity<>(orderV2, HttpStatus.CREATED);
    }


    /**
     * No of consumers = no of partitions + 1
     *
     *
     *  -- with in patition --sequential
     *  -- across partitions -- parallel
     *
     *
     */


}

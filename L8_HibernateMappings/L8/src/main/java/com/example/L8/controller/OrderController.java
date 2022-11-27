package com.example.L8.controller;

import com.example.L8.requests.CreateOrdersRequest;
import com.example.L8.service.OrderService;
import com.example.L8.utils.ResponseGenerator;
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
    ResponseGenerator responseGenerator;

    @Autowired
    OrderService orderService;

    /**
     *  what would be the flow in layman terms when an order is placed
     *
     * @TRansaction / you would need to manually handle stuff
     *      check if all book is present or not..
     *              --> then what ?
     *              Create an order in pending state
     *                      --> mark all the books as not available / booked
     *                                          --------------------------------> Take payment from a user
     *                                                                      (Payment)
     *                  --> mark the order success
     *                  done
     *
     *
     * Problems existing
     *
     *
     *      ---> N + 1 problem
     *      --> Stack / Memory Overflow problem
     *
     */

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewUser(@Valid @RequestBody CreateOrdersRequest request){
        return responseGenerator.generateResponse(orderService.createOrderV2(request), HttpStatus.CREATED);
    }



}

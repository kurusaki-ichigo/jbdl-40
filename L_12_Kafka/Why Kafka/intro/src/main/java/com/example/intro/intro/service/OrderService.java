package com.example.intro.intro.service;

import com.example.intro.intro.entity.Orders;
import com.example.intro.intro.repository.OrderRepository;
import com.example.intro.intro.request.CreateOrdersRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class OrderService {



    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Transactional
    @SneakyThrows
    public Orders createOrderV2(CreateOrdersRequest request) {
        Orders order = request.toOrders();
        /**
         * user microservice
         * and persist data
         */
        String s = restTemplate.postForObject("http://localhost:8081/user", request.getPaxInfo(), String.class);
        CreateOrdersRequest.PaxInfo paxInfo = objectMapper.readValue(s, CreateOrdersRequest.PaxInfo.class);
        order.setUserIdentifier(paxInfo.getUserId());
        return saveOrUpdate(order);
    }




    public Orders saveOrUpdate(Orders order){
        return orderRepository.save(order);
    }


}

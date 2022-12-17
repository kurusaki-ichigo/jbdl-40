package com.example.kafka.kafka.service;

import com.example.kafka.kafka.entity.Orders;
import com.example.kafka.kafka.repository.OrderRepository;
import com.example.kafka.kafka.request.CreateOrdersRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.kafka.kafka.KafkaApplication.ORDER_CREATED;


@Service
@Slf4j
public class OrderService {



    @Autowired
    OrderRepository orderRepository;

    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;

    @Transactional
    @SneakyThrows
    public Orders createOrderV2(CreateOrdersRequest request) {
        Orders order = request.toOrders();
        return saveOrUpdate(order);
    }

    public void sendMessageToKafka(UUID orderId){
        kafkaTemplate.send(ORDER_CREATED, orderId.toString());
    }


    /**
     *
     * CHANGE DATA CAPTURE
     * @param order
     * @return
     *
     *
     * -- read not from application
     * but from mysql bin logs
     *
     *
     *          Mysql bin logs ------> DEBEZIUM / MAXWELL ------> KafkaTopics <---- SB consumer
     *
     *
     */



    public Orders saveOrUpdate(Orders order){
        return orderRepository.save(order);
    }


}

package com.example.ewallet.transactions.listener;

import com.example.ewallet.transactions.entity.TransactionStatus;
import com.example.ewallet.transactions.models.TransactionInfo;
import com.example.ewallet.transactions.service.TransactingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionListener {

    public static final String TRANSACTION_FAILURE = "TRANSACTION_FAILURE";
    public static final String TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TransactingService transactingService;


    @SneakyThrows
    @KafkaListener(topics = {TRANSACTION_FAILURE}, groupId = "transaction_group")
    public void consumerTransactionFailureMessage(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        String message = consumerRecord.value();
        TransactionInfo transactionInfo = objectMapper.readValue(message, TransactionInfo.class);
        transactingService.updateRecord(transactionInfo, TransactionStatus.ERROR);
    }

    @SneakyThrows
    @KafkaListener(topics = {TRANSACTION_SUCCESS}, groupId = "transaction_group")
    public void consumerTransactionSuccessMessage(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        String message = consumerRecord.value();
        TransactionInfo transactionInfo = objectMapper.readValue(message, TransactionInfo.class);
        transactingService.updateRecord(transactionInfo, TransactionStatus.SUCCESS);
    }
}

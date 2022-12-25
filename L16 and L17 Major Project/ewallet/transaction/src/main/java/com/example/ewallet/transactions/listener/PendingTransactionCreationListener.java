package com.example.ewallet.transactions.listener;

import com.example.ewallet.transactions.entity.Transaction;
import com.example.ewallet.transactions.models.TransactionUser;
import com.example.ewallet.transactions.service.TransactingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PendingTransactionCreationListener {


    public static final String NEW_WALLET_CREATION = "NEW_WALLET_CREATION";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TransactingService transactingService;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    public static final String PENDING_TRANSACTION_CREATED = "PENDING_TRANSACTION_CREATED";


    @SneakyThrows
    @KafkaListener(topics = {NEW_WALLET_CREATION}, groupId = "transaction_group")
    public void createNewPendingTransaction(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        TransactionUser transactionUser = objectMapper.readValue(consumerRecord.value(), TransactionUser.class);
        Transaction newTransaction = transactingService.createNewTransaction(transactionUser);
        /**
         * ** Important **
         *          publish the messages of the same user in same partition -- so that transactions of user
         *          are processed sequentially
         */
        kafkaTemplate.send(PENDING_TRANSACTION_CREATED, newTransaction.getToWalletId().toString(), objectMapper.writeValueAsString(newTransaction));
    }


    /**
     *
     *
     *          1 - 1000
     *          2 - 2000
     *          3 - 3000
     *          4 - 4000
     *          5 - 1000
     *
     *
     *          [1]
     *          [5]
     *          [4,3,2]
     *
     *
     *
     *          [5,4,3,2,1]
     * 10,000
     *
     *
     */


}

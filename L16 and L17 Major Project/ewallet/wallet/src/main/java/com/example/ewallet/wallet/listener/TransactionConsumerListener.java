package com.example.ewallet.wallet.listener;

import com.example.ewallet.wallet.exception.InsufficientFundsException;
import com.example.ewallet.wallet.exception.InvalidTransactionReceivedException;
import com.example.ewallet.wallet.exception.WalletNotFoundException;
import com.example.ewallet.wallet.models.Status;
import com.example.ewallet.wallet.models.TransactionInfo;
import com.example.ewallet.wallet.models.WalletUser;
import com.example.ewallet.wallet.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.ewallet.wallet.models.Status.PENDING;

@Component
@Slf4j
public class TransactionConsumerListener {

    public static final String PENDING_TRANSACTION_CREATED = "PENDING_TRANSACTION_CREATED";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WalletService walletService;


    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;


    public static final String TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";

    public static final String TRANSACTION_FAILURE = "TRANSACTION_FAILURE";

    @KafkaListener(topics = {PENDING_TRANSACTION_CREATED}, groupId = "wallet_group")
    @SneakyThrows
    public void consumePendingTransactions(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        String message = consumerRecord.value();
        TransactionInfo transactionInfo = objectMapper.readValue(message, TransactionInfo.class);
        try {
            if(transactionInfo.getTransactionStatus() != PENDING){
                throw new InvalidTransactionReceivedException();
            }
            walletService.performTransaction(transactionInfo);
            transactionInfo.setTransactionStatus(Status.SUCCESS);
            kafkaTemplate.send(TRANSACTION_SUCCESS,transactionInfo.getToWalletId().toString(), objectMapper.writeValueAsString(transactionInfo));
        } catch (WalletNotFoundException | InsufficientFundsException | InvalidTransactionReceivedException exception){
            kafkaTemplate.send(TRANSACTION_FAILURE, transactionInfo.getTransactionId().toString(),message);
        }
    }





}

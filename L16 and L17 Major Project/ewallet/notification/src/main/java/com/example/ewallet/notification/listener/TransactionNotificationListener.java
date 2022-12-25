package com.example.ewallet.notification.listener;

import com.example.ewallet.notification.models.UserInfo;
import com.example.ewallet.notification.models.WalletInfo;
import com.example.ewallet.notification.service.NotificationService;
import com.example.ewallet.notification.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class TransactionNotificationListener {

    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    NotificationService notificationService;

    @Autowired
    UserService userService;

    @Value("${system.wallet.id}")
    UUID systemWalletId;

    public static final String TRANSACTION_FAILURE = "TRANSACTION_FAILURE";
    public static final String TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";

    @SneakyThrows
    @KafkaListener(topics = {TRANSACTION_FAILURE}, groupId = "notification_group")
    public void consumerTransactionFailureMessage(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        String message = consumerRecord.value();
        WalletInfo walletInfo = objectMapper.readValue(message, WalletInfo.class);

        if(!Objects.equals(systemWalletId, walletInfo.getFromWalletId())){
            UserInfo fromUser = userService.fetchUserInfo(walletInfo.getFromUserId());
            notificationService.sendAmountDeductionEmail(fromUser, walletInfo.getAmount());
        }

        if(!Objects.equals(systemWalletId, walletInfo.getToWalletId())){
            UserInfo toUser = userService.fetchUserInfo(walletInfo.getToUserId());
            notificationService.sendAmountCreditEmail(toUser, walletInfo.getAmount());
        }
    }

    @SneakyThrows
    @KafkaListener(topics = {TRANSACTION_SUCCESS}, groupId = "notification_group")
    public void consumerTransactionSuccessMessage(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        String message = consumerRecord.value();
        WalletInfo walletInfo = objectMapper.readValue(message, WalletInfo.class);

        if(!Objects.equals(systemWalletId, walletInfo.getFromWalletId())){
            UserInfo fromUser = userService.fetchUserInfo(walletInfo.getFromUserId());
            notificationService.sendAmountCreditEmail(fromUser, walletInfo.getAmount());
        }
        if(!Objects.equals(systemWalletId, walletInfo.getToWalletId())){
            UserInfo toUser = userService.fetchUserInfo(walletInfo.getToUserId());
            notificationService.sendAmountDeductionEmail(toUser, walletInfo.getAmount());
        }
    }
}

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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@Slf4j
public class NewUserCreationListener {


    public static final String NEW_WALLET_CREATION = "NEW_WALLET_CREATION";


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;


    @Autowired
    NotificationService notificationService;

    @SneakyThrows
    @KafkaListener(topics = {NEW_WALLET_CREATION}, groupId = "wallet_group")
    public void consumerNewUserCreationMessage(@Payload ConsumerRecord<String , String> consumerRecord) {
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        String message = consumerRecord.value();
        WalletInfo walletInfo = objectMapper.readValue(message, WalletInfo.class);
        /**
         * email of the user with the wallet
         * fetch userId of the user from wallet service
         */
        UserInfo userInfo = userService.fetchUserInfo(walletInfo.getToUserId());
        notificationService.sendNewUserOnboardingEmail(userInfo);
    }


}

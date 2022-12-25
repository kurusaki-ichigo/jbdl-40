package com.example.ewallet.wallet.listener;

import com.example.ewallet.wallet.entity.Wallet;
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

@Component
@Slf4j
public class NewUserCreationListener {

    public static final String NEW_USER_CREATED = "NEW_USER_CREATED";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WalletService walletService;


    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;

    public static final String NEW_WALLET_CREATION = "NEW_WALLET_CREATION";

    @Value("${system.wallet.id}")
    UUID systemWalletId;

    @Value("${system.wallet.id}")
    UUID systemUserId;

    @Value("${system.promo.balance}")
    Double promoBalance;


    @SneakyThrows
    @KafkaListener(topics = {NEW_USER_CREATED}, groupId = "wallet_group")
    public void consumerNewUserCreationMessage(@Payload ConsumerRecord<String , String> consumerRecord){
        log.info(" received [{},{}] {} - {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.topic(), consumerRecord.value());
        String message = consumerRecord.value();
        WalletUser walletUser = objectMapper.readValue(message, WalletUser.class);
        Wallet newWallet = walletService.createNewWallet(walletUser);
        TransactionInfo transactionInfo = TransactionInfo.builder()
                .toUserId(walletUser.getUserId())
                .toWalletId(newWallet.getWalletId())
                .fromWalletId(systemWalletId)
                .fromUserId(systemUserId)
                .amount(promoBalance)
                .build();
        kafkaTemplate.send(NEW_WALLET_CREATION, objectMapper.writeValueAsString(transactionInfo));
    }
}

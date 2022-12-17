package com.example.kafka.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.kafka.kafka.KafkaApplication.ORDER_CREATED;

@Component
public class NotificationConsumer {


    @Autowired
    JavaMailSender mailSender;

    @KafkaListener(topics = {ORDER_CREATED}, groupId = "notification_group")
    public void sendEmailToUser(@Payload String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("alvrk2@gmail.com");
        simpleMailMessage.setFrom("pmiglani1994@gmail.com");
        simpleMailMessage.setSubject("Your Order is Success");
        simpleMailMessage.setText(" Hey ... ! your order with id " + message +
                " is successfully placed with us.. our logistic team would soon " +
                "be in touch with you");
        mailSender.send(simpleMailMessage);
    }


}

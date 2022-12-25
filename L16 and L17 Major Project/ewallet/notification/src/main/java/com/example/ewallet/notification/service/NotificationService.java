package com.example.ewallet.notification.service;

import com.example.ewallet.notification.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    @Autowired
    JavaMailSender javaMailSender;



    @Value("${spring.mail.username}")
    String fromEmailId;

    public void sendNewUserOnboardingEmail(UserInfo userDetails) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Welcome " + userDetails.getData().getName());
        simpleMailMessage.setText("Hi!, welcome to our payment service platform. You have successfully created an account with us." +
                "This email is a welcome email.");
        simpleMailMessage.setTo(userDetails.getData().getEmail());
        simpleMailMessage.setFrom(fromEmailId);
        javaMailSender.send(simpleMailMessage);
    }


    public void sendAmountDeductionEmail(UserInfo userDetails, Double amount){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Balance Reduced ! " + userDetails.getData().getName());
        simpleMailMessage.setText("Hi!, your account balance is reduced by " + amount + "." +
                " Kindly check if it is legitimate transaction and report to us in case of fraud");
        simpleMailMessage.setTo(userDetails.getData().getEmail());
        simpleMailMessage.setFrom(fromEmailId);
        javaMailSender.send(simpleMailMessage);
    }

    public void sendAmountCreditEmail(UserInfo userDetails, Double amount){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Balance Increased ! " + userDetails.getData().getName());
        simpleMailMessage.setText("Hi!, your account balance is topped up by " + amount + "." +
                " Thanks for your continuous support to our gateway service");
        simpleMailMessage.setTo(userDetails.getData().getEmail());
        simpleMailMessage.setFrom(fromEmailId);
        javaMailSender.send(simpleMailMessage);
    }






}

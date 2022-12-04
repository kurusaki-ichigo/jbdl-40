package com.example.mappings.mapping.controller;

import com.example.mappings.mapping.requests.CreateNotificationDto;
import com.example.mappings.mapping.service.NotificationService;
import com.example.mappings.mapping.service.NotificationServiceV2;
import com.example.mappings.mapping.utils.ResponseGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NotificationController {

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationServiceV2 notificationServiceV2;


    @PostMapping(value = "/v1/notifications", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNotificationV1(@Valid @RequestBody CreateNotificationDto notificationDto) {
        log.info("Request Received {} ", notificationDto);
        return responseGenerator.generateResponse(notificationService.createNewNotification(notificationDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/v2/notifications", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNotificationV2(@Valid @RequestBody CreateNotificationDto notificationDto) {
        log.info("Request Received {} ", notificationDto);
        return responseGenerator.generateResponse(notificationServiceV2.createNewNotification(notificationDto), HttpStatus.CREATED);
    }

}

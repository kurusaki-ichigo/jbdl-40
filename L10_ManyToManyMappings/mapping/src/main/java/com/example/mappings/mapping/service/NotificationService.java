package com.example.mappings.mapping.service;

import com.example.mappings.mapping.compositeKey.StatusCodes;
import com.example.mappings.mapping.compositeKey.embedable.Notifications;
import com.example.mappings.mapping.exceptions.DuplicateNotificationException;
import com.example.mappings.mapping.repository.NotificationRepository;
import com.example.mappings.mapping.requests.CreateNotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    NotificationRepository repository;





    public Notifications createNewNotification(CreateNotificationDto request){
        Notifications notifications = request.toNotifications();
        Optional<Notifications> byId = repository.findById(notifications.getNotificationsId());
        if(byId.isPresent()){
            throw new DuplicateNotificationException(StatusCodes.DUPLICATE_NOTIFICATION);
        }
        return saveOrUpdate(notifications);
    }


    public Notifications saveOrUpdate(Notifications notifications){
        return repository.save(notifications);
    }
}

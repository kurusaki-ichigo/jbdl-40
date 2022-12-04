package com.example.mappings.mapping.service;

import com.example.mappings.mapping.compositeKey.IdClass.NotificationIdV2;
import com.example.mappings.mapping.compositeKey.IdClass.NotificationsV2;
import com.example.mappings.mapping.compositeKey.StatusCodes;
import com.example.mappings.mapping.exceptions.DuplicateNotificationException;
import com.example.mappings.mapping.repository.NotificationV2Repository;
import com.example.mappings.mapping.requests.CreateNotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceV2 {

    @Autowired
    NotificationV2Repository repository;


    public NotificationsV2 createNewNotification(CreateNotificationDto request){
        NotificationsV2 notifications = request.toNotificationsV2();
        NotificationIdV2 id = new NotificationIdV2(notifications.getIdempotencyKey(), notifications.getSource());
        Optional<NotificationsV2> byId = repository.findById(id);
        if(byId.isPresent()){
            throw new DuplicateNotificationException(StatusCodes.DUPLICATE_NOTIFICATION);
        }
        return saveOrUpdate(notifications);
    }


    public NotificationsV2 saveOrUpdate(NotificationsV2 notifications){
        return repository.save(notifications);
    }
}

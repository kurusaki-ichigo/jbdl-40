package com.example.mappings.mapping.repository;

import com.example.mappings.mapping.compositeKey.embedable.Notifications;
import com.example.mappings.mapping.compositeKey.embedable.NotificationsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, NotificationsId> {

    @Query("select notification from Notifications notification where notification.notificationsId.source = :sample")
    List<Notifications> findAllBySource(@Param("sample") String source);
}

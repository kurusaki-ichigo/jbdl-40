package com.example.mappings.mapping.repository;

import com.example.mappings.mapping.compositeKey.IdClass.NotificationIdV2;
import com.example.mappings.mapping.compositeKey.IdClass.NotificationsV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationV2Repository extends JpaRepository<NotificationsV2, NotificationIdV2> {

    List<NotificationsV2> findAllBySource(String source);
}

package com.example.mappings.mapping.compositeKey.embedable;


import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notifications {


    @EmbeddedId
    private NotificationsId notificationsId;
    private String channelValue;
    @Enumerated(value = EnumType.STRING)
    private ChannelType channelType;
    String templateId;




}

package com.example.mappings.mapping.compositeKey.IdClass;

import com.example.mappings.mapping.compositeKey.embedable.ChannelType;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(NotificationIdV2.class)
public class NotificationsV2 {

    @Id
    private String idempotencyKey;
    @Id
    private String source;


    private String channelValue;
    @Enumerated(value = EnumType.STRING)
    private ChannelType channelType;
    String templateId;

}

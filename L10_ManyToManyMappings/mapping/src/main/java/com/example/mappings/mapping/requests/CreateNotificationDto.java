package com.example.mappings.mapping.requests;


import com.example.mappings.mapping.compositeKey.IdClass.NotificationsV2;
import com.example.mappings.mapping.compositeKey.embedable.ChannelType;
import com.example.mappings.mapping.compositeKey.embedable.Notifications;
import com.example.mappings.mapping.compositeKey.embedable.NotificationsId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateNotificationDto {

    @NotNull
    private String idempotencyKey;
    @NotNull
    private String source;
    @NotNull
    private String channelValue;
    private ChannelType channelType;
    String templateId;

    public Notifications toNotifications(){
        return Notifications.builder()
                .notificationsId(new NotificationsId(idempotencyKey, source))
                .channelType(channelType)
                .channelValue(channelValue)
                .templateId(templateId).build();
    }


    public NotificationsV2 toNotificationsV2(){
        return NotificationsV2.builder()
                .idempotencyKey(idempotencyKey)
                .source(source)
                .channelType(channelType)
                .channelValue(channelValue)
                .templateId(templateId).build();
    }

}

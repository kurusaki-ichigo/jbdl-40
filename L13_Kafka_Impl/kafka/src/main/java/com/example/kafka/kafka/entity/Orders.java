package com.example.kafka.kafka.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID orderId;

    @Version
    private long version;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    String source;
    String destination;

    UUID userIdentifier;

    String doj;


    @PrePersist
    public void markInitiated(){
        this.orderStatus = OrderStatus.INITIATED;
    }

}

package com.example.L8.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID addressId;

    private String line1;
    private String line2;
    private String line3;
    private String line4;

    private String pincode;
    private String state;
    private String country;

    @CreationTimestamp
    private OffsetDateTime creationAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;


}

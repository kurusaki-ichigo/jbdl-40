package com.example.ewallet.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.envers.Audited;

import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"createdAt", "updatedAt" })
@JsonInclude(JsonInclude.Include.NON_NULL)
@Audited
public class Wallet {


    @Id
    @UuidGenerator
    @GeneratedValue
    UUID walletId;


    Double balance;
    UUID userId;

    @Version
    Long version;

    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

}

package com.example.ewallet.transactions.entity;

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
public class Transaction {

    @Id
    @UuidGenerator
    @GeneratedValue
    UUID transactionId;
    Double amount;
    UUID fromWalletId;
    UUID toWalletId;
    UUID fromUserId;
    UUID toUserId;

    @Enumerated(value = EnumType.STRING)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;


    @PrePersist
    public void prePersist(){
        this.transactionStatus = TransactionStatus.PENDING;
    }

}

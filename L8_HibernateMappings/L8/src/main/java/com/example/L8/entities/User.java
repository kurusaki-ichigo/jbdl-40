package com.example.L8.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID userId;

    private String name;

    private String email;

    /**
     * fetch strategy
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orderList;

    @CreationTimestamp
    private OffsetDateTime creationAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;


}

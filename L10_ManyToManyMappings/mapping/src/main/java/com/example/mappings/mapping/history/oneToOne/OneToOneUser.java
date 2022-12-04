package com.example.mappings.mapping.history.oneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class OneToOneUser {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID userId;

    private String name;

    private String email;


}

package com.example.user.user.entity;


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
public class UserEntity {


    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID userId;

    @Enumerated(value = EnumType.STRING)
    private FoodPreference foodPreference;

    String email;
    String name;

    /**
     @Data
     @NoArgsConstructor
     @JsonInclude(JsonInclude.Include.NON_NULL)
     public static class PaxInfo implements Serializable {
     public FoodPreference foodPreference;
     public String email;
     public String name;
     public UUID userId;
     }
     */
}

package com.example.mappings.mapping.history.oneToOne;

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
public class UserProfile {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID userProfileId;



    @OneToOne
    @JoinColumn
    private OneToOneUser user;


}

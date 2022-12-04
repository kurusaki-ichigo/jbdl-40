package com.example.mappings.mapping.history.oneToMany;

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
public class Order {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID orderId;


    @ManyToOne
    @JoinColumn
    private OneToManyUser associatedUser;

}

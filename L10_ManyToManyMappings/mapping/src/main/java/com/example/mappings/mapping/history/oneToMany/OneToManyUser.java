package com.example.mappings.mapping.history.oneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OneToManyUser {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID userId;


    @OneToMany(mappedBy = "associatedUser")
    private List<Order> userOrders;
}

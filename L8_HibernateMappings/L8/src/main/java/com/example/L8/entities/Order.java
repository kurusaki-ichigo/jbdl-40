package com.example.L8.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
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


    @Version
    private long version;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private Double cartAmount;


    @ManyToOne
    @JoinColumn
    private User user;


    @OneToMany(mappedBy = "order")
    @JsonIgnore
    List<Books> booksList;


}

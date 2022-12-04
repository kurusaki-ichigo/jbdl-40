package com.example.mappings.mapping.manyToMany.firstWay;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Deprecated
public class Books {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID booksId;

    @ManyToMany
    @JoinTable(name = "inventory",
    joinColumns = @JoinColumn(name = "booksId"),
    inverseJoinColumns = @JoinColumn(name = "authorId"))
    Set<Authors> inventoryAuthors;



}

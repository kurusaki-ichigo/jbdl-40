package com.example.mappings.mapping.manyToMany.firstWay;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Authors {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID authorId;


    @ManyToMany(mappedBy = "inventoryAuthors")
    Set<Books> associatedBooks;

}

package com.example.L8.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Authors {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID authorId;

    private String name;

    private String email;

    /**
     * Lazy fetch strategy
     */
    @OneToMany(mappedBy = "authors")
    @JsonIgnore
    List<Books> associateBooks;


    @CreationTimestamp
    private OffsetDateTime creationAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

}

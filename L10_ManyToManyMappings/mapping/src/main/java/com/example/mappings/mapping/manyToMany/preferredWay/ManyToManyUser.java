package com.example.mappings.mapping.manyToMany.preferredWay;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class ManyToManyUser {


    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID userId;


    @OneToMany(mappedBy = "associatedUser")
    private List<CourseRating> courseRatings;


}

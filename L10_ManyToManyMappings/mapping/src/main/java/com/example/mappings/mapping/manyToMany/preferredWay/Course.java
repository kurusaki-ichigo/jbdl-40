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
public class Course {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID courseId;

    @OneToMany(mappedBy = "associatedCourse")
    private List<CourseRating> courseRatings;


}

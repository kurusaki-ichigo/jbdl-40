package com.example.mappings.mapping.manyToMany.preferredWay;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourseRating {


    @EmbeddedId
    CourseRatingId courseRatingId;

    @ManyToOne
    @MapsId(value = "userId")
    ManyToManyUser associatedUser;

    @ManyToOne
    @MapsId(value = "courseId")
    Course associatedCourse;


    int ratings;
    String review;
}

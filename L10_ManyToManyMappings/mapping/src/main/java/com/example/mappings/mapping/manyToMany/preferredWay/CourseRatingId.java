package com.example.mappings.mapping.manyToMany.preferredWay;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 */
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CourseRatingId implements Serializable {
    private static final long serialVersionUID = 8354532590831702386L;

    private UUID courseId;
    private UUID userId;
}

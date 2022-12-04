package com.example.mappings.mapping.compositeKey.embedable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Equals and hashCode
 * Implements serializable
 */
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NotificationsId implements Serializable {

    private static final long serialVersionUID = 8354532590831702386L;
    private String idempotencyKey;
    private String source;
}

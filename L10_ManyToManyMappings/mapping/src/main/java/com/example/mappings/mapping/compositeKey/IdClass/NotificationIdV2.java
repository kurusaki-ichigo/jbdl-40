package com.example.mappings.mapping.compositeKey.IdClass;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Equals and hashCode
 * Implements serializable
 */

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NotificationIdV2 implements Serializable {
    private static final long serialVersionUID = 835453231702386L;

    private String idempotencyKey;
    private String source;


}

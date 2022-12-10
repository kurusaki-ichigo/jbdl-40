package com.example.cache.cache.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Books implements Serializable {

    private static final long serialVersionUID = 5706988877334359930L;

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID bookId;

    private String name;

    private String isbn;

    private Double purchaseAmount;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @CreationTimestamp
    private OffsetDateTime creationAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @PrePersist
    public void markAvailable(){
        this.bookStatus = BookStatus.AVAILABLE;
    }

}

package com.example.L8.entities;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Books {

    /**
     *
     *  Why you should not used intergers as Id to entities
     *  but rather UUID.
     *              random Id which incorporates timestamp too.
     *
     *
     */
    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID bookId;


    @Version
    private long version;

    private String name;

    private String isbn;

    private Double purchaseAmount;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne
    @JoinColumn
    private Order order;

    /**
     * loose coupling and tight coupling
     *
     *
     */
    @ManyToOne
    @JoinColumn
    private Authors authors;

    @CreationTimestamp
    private OffsetDateTime creationAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    /**
     *      Entity lifecycle
     *
     *      (new state) ----> is not present in the database;
     *
     *      Hooks available around the entity lifecycle provided by the JPA
     *
     *      PrePersist (save into database) PostPersist
     *       PreUpdate           (update)       POstUpdate
     *        PreRemove              (remove/delete) POstRemove
     *
     */
    @PrePersist
    public void markAvailable(){
        this.bookStatus = BookStatus.AVAILABLE;
    }

    public boolean isAvailable(){
        return bookStatus == BookStatus.AVAILABLE;
    }



}

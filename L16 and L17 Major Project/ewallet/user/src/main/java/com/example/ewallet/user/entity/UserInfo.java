package com.example.ewallet.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
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
@Table(indexes = {@Index(name = "UNIQUE_EMAIL", columnList = "email", unique = true)})
@JsonIgnoreProperties(value = {"createdAt", "updatedAt" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo implements Serializable {

    /**
     *  MVC
     *      --> MODEL --> REQUEST       ---> Controller --> service --> DAO-->  Object
     *          VIEW --> RESPONSE
     */
    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID userId;
    private String name;
    private String email;
    private String promoCode;



    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;


    @PrePersist
    public void initUser(){
        this.promoCode = UUID.randomUUID().toString();
    }



}

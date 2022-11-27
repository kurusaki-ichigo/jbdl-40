package com.example.L8.repository;

import com.example.L8.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, UUID> {

    Optional<Authors> findByEmail(String email);
}

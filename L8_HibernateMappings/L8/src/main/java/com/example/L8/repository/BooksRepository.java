package com.example.L8.repository;

import com.example.L8.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BooksRepository extends JpaRepository<Books, UUID> {

    Optional<Books> findByIsbn(String isbn);

    List<Books> findAllByNameLike(String contains);
}

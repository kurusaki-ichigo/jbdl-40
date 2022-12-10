package com.example.cache.cache.repository;

import com.example.cache.cache.entity.Books;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BooksRepository extends JpaRepository<Books, UUID> {

//    @Cacheable(cacheNames = "booksCache")
    List<Books> findAllByIsbn(String isbn);

    Optional<Books> findByIsbn(String isbn);

    List<Books> findAllByNameLike(String contains);

    Page<Books> findAllByCreationAtBefore(OffsetDateTime before , PageRequest pageRequest);
}

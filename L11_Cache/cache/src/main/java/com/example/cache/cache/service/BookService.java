package com.example.cache.cache.service;

import com.example.cache.cache.entity.BookFetchType;
import com.example.cache.cache.entity.BookStatus;
import com.example.cache.cache.entity.Books;
import com.example.cache.cache.entity.StatusCodes;
import com.example.cache.cache.exceptions.BookNotAvailableException;
import com.example.cache.cache.exceptions.DuplicateBookException;
import com.example.cache.cache.repository.BooksRepository;
import com.example.cache.cache.request.CreateNewBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    @Autowired
    BooksRepository repository;

    @Autowired
    RedisTemplate<String ,Object> redisTemplate;

    public static final String BOOKS_CACHE_PREFIX = "booksCache::";


    /**
     *                                  --> check if exists ( by isbn )
     *                                              --> if exists
     *                                                      --> throw exception duplicate entry
     *                                             --> if it does not
     *                                                      ---> create new book
     * @param request
     * @return
     */
    public Books createBook(CreateNewBookRequest request) {

        /**
         *
         */
        Optional<Books> existingBook = repository.findByIsbn(request.getBookIsbn());
        if(existingBook.isPresent()){
            throw new DuplicateBookException(StatusCodes.DUPLICATE_BOOK);
        }
        Books books = request.toBook();
        return saveOrUpdate(books);
    }


    public List<Books> fetchBookByQueryParams(BookFetchType fetchType, String fetchTypeValue){
        return switch (fetchType) {
            case LIKE -> findAllBooksNameContaining(fetchTypeValue);
            case ISBN -> findAllByISBN(fetchTypeValue);
        };
    }


    /**
     * booksCache::124w3sa
     * @param isbn
     * @return
     */
    public List<Books> findAllByISBN(String isbn){
        /**
         * check if data exists in redis
         *  -- if yes return value
         *
         *
         *  - if no
         *          ---> fetch data from Db
         *                  --> if data exists
         *                          --> insert into redis
         *                   ==> if not
         *                          return empty to FE
         */
        Object objectVal = redisTemplate.opsForValue().get(getISBNRedisKey(isbn));
        if(Objects.nonNull(objectVal)){
            return (List<Books>) objectVal;
        }

        List<Books> allByIsbn = repository.findAllByIsbn(isbn);
        if(!CollectionUtils.isEmpty(allByIsbn)){
            redisTemplate.opsForValue().set(getISBNRedisKey(isbn), allByIsbn, Duration.ofMinutes(2));
        }

        return allByIsbn;
    }


    public String getISBNRedisKey(String isbn){
        return BOOKS_CACHE_PREFIX + isbn;
    }



    public Page<Books> fetchAllBooks(int pageNumber){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(pageNumber, 30, sort);
        return repository.findAllByCreationAtBefore(OffsetDateTime.now(), pageRequest);
    }
    public Optional<Books> findByUUId(String uuid){
        UUID bookUuid = Optional.of(uuid).map(UUID::fromString)
                .orElseThrow(() -> new BookNotAvailableException(StatusCodes.BOOK_UNAVAILABLE));
        return repository.findById(bookUuid);
    }

    public List<Books> findAllBooksNameContaining(String contains){
        return repository.findAllByNameLike(contains);
    }

    public List<Books> findAllByIds(List<String> bookIds){
        List<UUID> uuids = bookIds.stream().map(UUID::fromString).collect(Collectors.toList());
        return repository.findAllById(uuids);
    }

    public List<Books> purchaseBooks(List<Books> booksList){
        booksList.forEach(books -> books.setBookStatus(BookStatus.SOLD));
        return repository.saveAll(booksList);
    }

    public List<Books> refundBooks(List<Books> booksList){
        booksList.forEach(books -> books.setBookStatus(BookStatus.AVAILABLE));
        return repository.saveAll(booksList);
    }

    public Books saveOrUpdate(Books books){
        return repository.save(books);
    }
}

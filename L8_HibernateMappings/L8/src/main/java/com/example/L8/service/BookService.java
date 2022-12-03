package com.example.L8.service;

import com.example.L8.entities.Authors;
import com.example.L8.entities.BookStatus;
import com.example.L8.entities.Books;
import com.example.L8.exception.BookNotAvailableException;
import com.example.L8.exception.DuplicateBookException;
import com.example.L8.models.BookFetchType;
import com.example.L8.models.StatusCodes;
import com.example.L8.repository.BooksRepository;
import com.example.L8.requests.CreateNewBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    @Autowired
    BooksRepository repository;

    @Autowired
    AuthorService authorService;

    @Value("${fetch.all.pageSize}")
    Integer pageSize;

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

        Authors authors = authorService.createNewAuthor(request);

        /**
         *
         */
        Optional<Books> existingBook = repository.findByIsbn(request.getBookIsbn());
        if(existingBook.isPresent()){
            throw new DuplicateBookException(StatusCodes.DUPLICATE_BOOK);
        }
        Books books = request.toBook();
        books.setAuthors(authors);
        return saveOrUpdate(books);
    }


    public List<Books> fetchBookByQueryParams(BookFetchType fetchType, String fetchTypeValue){
        return switch (fetchType) {
            case LIKE -> findAllBooksNameContaining(fetchTypeValue);
            case ISBN -> repository.findAllByIsbn(fetchTypeValue);
        };
    }


    public Page<Books> fetchAllBooks(int pageNumber){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
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

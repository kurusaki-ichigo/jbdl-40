package com.example.L8.service;

import com.example.L8.entities.Authors;
import com.example.L8.entities.BookStatus;
import com.example.L8.entities.Books;
import com.example.L8.exception.DuplicateBookException;
import com.example.L8.models.StatusCodes;
import com.example.L8.repository.BooksRepository;
import com.example.L8.requests.CreateNewBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

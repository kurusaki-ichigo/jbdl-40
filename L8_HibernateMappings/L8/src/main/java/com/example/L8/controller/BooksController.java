package com.example.L8.controller;

import com.example.L8.requests.CreateNewBookRequest;
import com.example.L8.service.BookService;
import com.example.L8.utils.ResponseGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BooksController {

    @Autowired
    ResponseGenerator responseGenerator;


    @Autowired
    BookService bookService;


    /**
     *
     *  {bookdata + authordata}
     *
     *              layman flow
     *              author ---> check if author exists (same email)
     *                                      --> if exists (return author)
     *                                              -- else create new author
     *
     *
     *              Book
     *                      ----> create book
     *                                  --> check if exists ( by isbn )
     *                                              --> if exists
     *                                                      --> throw exception duplicate entry
     *                                             --> if it does not
     *                                                      ---> create new book
     *
     *
     *    H.W.
     *    Implement a get api to fetch the book
     *
     *
     *
     *
     * @param request
     * @return
     */

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBook(@Valid @RequestBody CreateNewBookRequest request){
        return responseGenerator.generateResponse(bookService.createBook(request), HttpStatus.CREATED);
    }


    /**
     * layman
     *          fetch the book by
     *
     *
     *         return all books
     *
     *                  --> fuzzy serarch
     *                      Harry Potter
     *                      arr
     *                      you should return all the books having "arr" as present
     *                      Like Query
     *
     *                      select * from books where name like %arry%;
     */
    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBook(@RequestParam("contains") String contains){
        return responseGenerator.generateResponse(bookService.findAllBooksNameContaining(contains), HttpStatus.OK);
    }


    /**
     * find by ISBN
     */


}

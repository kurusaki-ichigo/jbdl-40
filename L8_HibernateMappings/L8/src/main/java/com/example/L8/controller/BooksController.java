package com.example.L8.controller;

import com.example.L8.models.BookFetchType;
import com.example.L8.requests.CreateNewBookRequest;
import com.example.L8.service.BookService;
import com.example.L8.utils.ResponseGenerator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        MDC.put("traceId", UUID.randomUUID().toString());
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
//    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> createBook(@RequestParam("contains") String contains){
//        return responseGenerator.generateResponse(bookService.findAllBooksNameContaining(contains), HttpStatus.OK);
//    }


    /**
     * find by ISBN - is same for same of books
     *
     *      Query param
     *      Path variable
     *              -- absolutely sure
     *              folder structure
     *         downloads    -    (movies)           - movie name (unique name) - (with havin size geater than 700 MB)
     *                                              - movie name 2
     *
     *
     *
     *                                                              in respect to rest
     *                                                                      -- ID
     *                                                                      -- unqiue attribute
     *                                                                      -- UUID
     *                                                                      -- unique (email ids)
     *
     *                                                                      (primary key vs unique key)
     *                                                                      (-- primary index vs secondary index -- )
     *                                                                      (BTree)
     *                                                                          --- different
     *                                                                              -- Primary key index (Table of contents of book)
     *                                                                              -- Secondary key index (Glossary)
     *
     *                      - fetchType : isbn
     *                                    like
     *                      - fetchTypeValue :  (book isbn value)
     *                                          (book name like)
     *
     */

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchBooksByQueryParams(@RequestParam("fetchType") BookFetchType fetchType,
                                                  @RequestParam("fetchTypeValue") String fetchTypeValue){
        MDC.put("traceId", UUID.randomUUID().toString());
        return responseGenerator.generateResponse(bookService.fetchBookByQueryParams(fetchType,fetchTypeValue), HttpStatus.OK);
    }

    /**
     * find by UUID
     */
    @GetMapping(value = "/book/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchBookById(@PathVariable(name = "uuid") String uuidSerial){
        MDC.put("traceId", UUID.randomUUID().toString());
        return responseGenerator.generateResponse(bookService.findByUUId(uuidSerial), HttpStatus.OK);
    }


    /**
     *
     * 1000's of book
     *      what should have been the strategy to display books
     *
     *          1) fetch all then show paginated view (ie page 2 , page 3 , page 4....)
     *
     *          2) fetch the page 1 only and (on click page 2, page 3, page 4 and so on..)
     *
     *          3) On scroll , half window -- render the next page results (intuitive)
     *
     *
     *
     *          1 --->
     *
     *          if you are downloading a movie of 1 GB
     *          vs
     *          downloading 100 MB of MOVie
     *
     *          download fast --- >   why ???????????
     *
     *                          What is happening in the background
     *
     *
     *                                  (Booking.com / trip advisor)
     *                                                              --- different supplier / vendors
     *
     *                                          in parallel             - amadeus  (100)
     *                                                                  - sabre     (200)
     *                                                                  - galileo   (100)
     *
     *                              Heavy operation
     *                                  Trip advisor
     *                                      ---> making a poll call
     *                                          FE --------------------> BE (hey did you got all result)
     *                                          <------------------- BE ( no still processing)
     *                                          After every 5 seconds --> FE ---> BE
     *                                                                      <---- till backend replied with Yes all results displayed.
     *
     *
     *          2
     *
     *
     *
     *  Hibernate N + 1 problems
     *
     *            ---> it will search for all of the books (find all by *********) 1 query
     *                      (n results)
     *                              ------> hibernate would query one by one -- fetch me the author
     *                              -------> n author hits being made to DB
     *
     *                              (N + 1) queries
     *
     *          ----> write custom queries
     *          Queries for it ..
     *
     *
     *
     *  Sorted by Name
     * @return
     */
    @GetMapping(value = "/books/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchAllBooks(@RequestParam(name = "pageNumber", defaultValue = "0")
                                                            Integer pageNumber){
        MDC.put("traceId", UUID.randomUUID().toString());
        return responseGenerator.generateResponse(bookService.fetchAllBooks(pageNumber), HttpStatus.OK);
    }


    /**
     *
     *
     * H.w.
     *
     * DELETE A BOOK
     *      ->  DELETE BOOK by ISBN
     *              --> check if the author exists -- ?
     *                  (Yes it would)
     *                      --> check if author has more than one book
     *                              --> if yes (dont delete author)
     *
     *                      --> if no
     *                              ---> delete the author as well.
     *
     *      --> HARD DELETE
     *      --> SOFT DELETE     - isActive - false;
     *                      ---> DIFFERENCE ---> ?
     *
     *      Archival Policy
     *                  ---> cold storage
     *            S3 --> Simple Storage Service (AWS - store files)
     *                          -- >
     *                          Storage mechanism
     *             Hot Storage          Cold Storage            Glacier Storage
     *            (cost -- more)        (cost -- cost less)     (cost -- very very less)
     *
     *              Hardware and other specs of storage device are different
     *
     *
     * CREATE A BOOK : ?
     *
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
     */


}

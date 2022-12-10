package com.example.cache.cache.controller;

import com.example.cache.cache.entity.BookFetchType;
import com.example.cache.cache.request.CreateNewBookRequest;
import com.example.cache.cache.service.BookService;
import com.example.cache.cache.utils.ResponseGenerator;
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



    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBook(@Valid @RequestBody CreateNewBookRequest request){
        MDC.put("traceId", UUID.randomUUID().toString());
        return responseGenerator.generateResponse(bookService.createBook(request), HttpStatus.CREATED);
    }


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


    @GetMapping(value = "/books/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchAllBooks(@RequestParam(name = "pageNumber", defaultValue = "0")
                                                            Integer pageNumber){
        MDC.put("traceId", UUID.randomUUID().toString());
        return responseGenerator.generateResponse(bookService.fetchAllBooks(pageNumber), HttpStatus.OK);
    }




}

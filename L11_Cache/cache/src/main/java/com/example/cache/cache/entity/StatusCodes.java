package com.example.cache.cache.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum StatusCodes {

    USER_NOT_FOUND("USER_NOT_FOUND", "User is not available", HttpStatus.BAD_REQUEST),
    BOOK_UNAVAILABLE("BOOK_UNAVAILABLE", "Book is not available", HttpStatus.BAD_REQUEST),
    DUPLICATE_BOOK("DUP_BOOK_001", "Duplicate Book Exists", HttpStatus.BAD_REQUEST),
    DUPLICATE_USER("DUP_USER_001", "Duplicate User Exists", HttpStatus.BAD_REQUEST);





    private  HttpStatus status;
    private String message;
    private  String code;


    StatusCodes(String code , String message, HttpStatus status){
        this.code = code;
        this.message = message;
        this.status = status;
    }

}

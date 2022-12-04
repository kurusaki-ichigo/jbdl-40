package com.example.mappings.mapping.compositeKey;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StatusCodes {

    DUPLICATE_NOTIFICATION("DUPLICATE_NOTIFICATION", "Duplicate Notification", HttpStatus.BAD_REQUEST),
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

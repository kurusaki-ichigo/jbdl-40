package com.example.L8.controller.advice;

import com.example.L8.exception.DuplicateBookException;
import com.example.L8.exception.DuplicateUserException;
import com.example.L8.utils.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @Autowired
    ResponseGenerator responseGenerator;

    @ExceptionHandler(value = DuplicateBookException.class)
    public ResponseEntity<String> generateExceptionResponse(DuplicateBookException duplicateBookException){
        return responseGenerator.generateExceptionResponse(duplicateBookException.getStatusCodes());
    }


    @ExceptionHandler(value = DuplicateUserException.class)
    public ResponseEntity<String> generateUserExceptionResponse(DuplicateUserException exception){
        return responseGenerator.generateExceptionResponse(exception.getStatusCodes());
    }


}

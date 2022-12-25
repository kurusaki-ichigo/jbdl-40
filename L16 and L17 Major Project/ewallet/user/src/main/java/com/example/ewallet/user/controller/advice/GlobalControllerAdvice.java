package com.example.ewallet.user.controller.advice;

import com.example.ewallet.user.exception.DuplicateUserException;
import com.example.ewallet.user.utils.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    ResponseGenerator responseGenerator;

    @ExceptionHandler(value = DuplicateUserException.class)
    public ResponseEntity<String> generateExceptionResponse(DuplicateUserException duplicateBookException){
        return responseGenerator.generateExceptionResponse(duplicateBookException.getStatusCode());
    }
}

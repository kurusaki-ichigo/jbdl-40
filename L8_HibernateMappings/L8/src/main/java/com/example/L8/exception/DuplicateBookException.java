package com.example.L8.exception;


import com.example.L8.models.StatusCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DuplicateBookException extends RuntimeException{

    private final StatusCodes statusCodes;


}

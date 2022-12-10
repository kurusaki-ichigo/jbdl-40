package com.example.cache.cache.exceptions;


import com.example.cache.cache.entity.StatusCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DuplicateBookException extends RuntimeException{

    private final StatusCodes statusCodes;


}

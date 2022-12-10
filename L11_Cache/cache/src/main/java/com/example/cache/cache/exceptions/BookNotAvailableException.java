package com.example.cache.cache.exceptions;

import com.example.cache.cache.entity.StatusCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookNotAvailableException extends RuntimeException{
    private final StatusCodes statusCodes;
}

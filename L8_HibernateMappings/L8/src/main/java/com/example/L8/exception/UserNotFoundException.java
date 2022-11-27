package com.example.L8.exception;

import com.example.L8.models.StatusCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException {
    private final StatusCodes statusCodes;
}

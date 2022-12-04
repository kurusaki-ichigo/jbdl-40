package com.example.mappings.mapping.exceptions;

import com.example.mappings.mapping.compositeKey.StatusCodes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DuplicateNotificationException extends RuntimeException{

   private final StatusCodes statusCodes;
}

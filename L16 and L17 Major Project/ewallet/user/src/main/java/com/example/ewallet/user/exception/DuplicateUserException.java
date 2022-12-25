package com.example.ewallet.user.exception;

import com.example.ewallet.user.models.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DuplicateUserException extends RuntimeException{
   public final StatusCode statusCode;

}

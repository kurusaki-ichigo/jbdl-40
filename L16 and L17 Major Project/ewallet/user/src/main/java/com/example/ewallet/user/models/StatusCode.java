package com.example.ewallet.user.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StatusCode {

    DUPLICATE_USER("DUP_USER_001","User Already Exists", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("UNF_USER_001","User Not Found", HttpStatus.BAD_REQUEST);


    public final String code;
    public final String message;
    public final HttpStatus status;

}

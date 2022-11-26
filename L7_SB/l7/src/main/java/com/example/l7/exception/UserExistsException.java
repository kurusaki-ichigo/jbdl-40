package com.example.l7.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserExistsException extends RuntimeException{

    public UserExistsException(String message){
        super(message);
    }

}

package com.example.L8.requests;

import com.example.L8.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateNewUserRequest {

    @NotNull
    String name;
    @Email
    String email;

    public User toUser(){
        return User.builder()
                .name(name)
                .email(email).build();
    }
}

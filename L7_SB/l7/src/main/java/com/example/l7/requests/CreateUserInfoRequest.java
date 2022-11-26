package com.example.l7.requests;

import com.example.l7.entities.UserEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class CreateUserInfoRequest {


    @NotEmpty
    String name;
    @Email
    String email;

    /**
     * you may also use mapstruct
     *
     * @return
     */
    public UserEntity toUser(){
        return UserEntity.builder()
                .name(name)
                .email(email)
                .build();
    }

}

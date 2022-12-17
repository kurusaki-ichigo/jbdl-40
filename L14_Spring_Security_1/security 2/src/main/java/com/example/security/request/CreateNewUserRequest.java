package com.example.security.request;

import com.example.security.entity.UserInfo;
import lombok.Data;

import java.util.Set;

@Data
public class CreateNewUserRequest {

    private String name;
    // username
    private String email;
    private String passwordRaw;
    private Set<String> roles;


    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .name(name)
                .email(email)
                .passwordRaw(passwordRaw)
                .roles(roles).build();
    }
}

package com.example.ewallet.user.request;

import com.example.ewallet.user.entity.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNewUserRequest {

    @NotBlank
    private String name;
    @Email
    private String email;

    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .name(name)
                .email(email).build();
    }


}

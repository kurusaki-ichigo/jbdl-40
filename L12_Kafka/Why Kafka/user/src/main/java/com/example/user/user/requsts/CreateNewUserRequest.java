package com.example.user.user.requsts;

import com.example.user.user.entity.FoodPreference;
import com.example.user.user.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateNewUserRequest {

    public FoodPreference foodPreference;
    public String email;
    public String name;


    public UserEntity toUser(){
        return UserEntity.builder()
                .email(email)
                .name(name)
                .foodPreference(foodPreference)
                .build();
    }

}

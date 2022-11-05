package com.example.exceptions.example.custom;

import com.example.exceptions.example.classCastException.user.UserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Service
 */
public class FetchUser {

    Map<String , UserInfo > idTOUserMap = new HashMap<>();
    public void findUser(String id){
        UserInfo userInfo = idTOUserMap.get(id);
        if(Objects.isNull(userInfo)){
            throw new UserNotFoundUncheckedException();
        }
    }

    public static void main(String[] args) {
        FetchUser fetchUser = new FetchUser();
        fetchUser.findUser("10");
    }

}

package com.example.exceptions.example.customCheckedException;

import com.example.exceptions.example.classCastException.user.UserInfo;
import com.example.exceptions.example.custom.FetchUser;
import com.example.exceptions.example.custom.UserNotFoundUncheckedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class FetchUserChecked {

    /**
     *
     * final keyword
     *  final class
     *          --> memory
     *          funtionalities are not required to be overridden
     *          Integer.class
     *                  -> dont want anyone to override ()
     *
     *  final -- keyword with respect variable  - once assigned , the value cannot be changed
     *
     *
     *
     */

    Map<String , UserInfo> idTOUserMap = new HashMap<>();
    public void findUser(String id) throws UserNotFoundException {
        UserInfo userInfo = idTOUserMap.get(id);
        if(Objects.isNull(userInfo)){
            throw new UserNotFoundException();
        }
    }


    public static void main(String[] args) {
        FetchUserChecked fetchUser = new FetchUserChecked();
        try {
            fetchUser.findUser("10");
        } catch (UserNotFoundException e) {
            System.out.println(" inside catch block ");
//            System.exit(0);
        } finally {

            /**
             *
             */
            System.out.println(" inside finally");
        }
    }

}

package com.example.collections.hashMap;

import com.example.exceptions.example.classCastException.user.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * TreeMap internal implementation
 */
public class HashMapInternalImplementation {

    /**
     * when are the two objects are said to be equal - ?
     *
     *      reference to the same address
     *      (how does the reference to the same memeory is obtained)
     *          using hashCode()
     *           -- where is this present
     *
     *
     *     HashMap - key value pair
     *
     *     prior
     *      Load Factor (ArrayList --> )
     *
     *      Array[fixed length] vs LinkedList --> Continuous memory allocation
     *
     *     Arraylist is closest implementation -> load factor
     *
     *
     *     let the key be integer
     *     Key , value pair
     *
     *     hash Of the key
     *     hashFunction is the modulo by 10
     *
     *     HashMap.put(25, "Abcd");
     *     HashMap.put(35, "dbeks");
     *     HashMap.put(50 , "norm")
     *     HashMap.put(35, "qwerty");
     *
     *     25 -- hash (25) -- >5
     *                                              |-35, dbeks--|
     *     |-50, norm--|                            |-25, Abcd--|
     *     [---0---|---1----|--2---|--3----|--4----|-5--|-6--|--7------|--8-----|-9--------]
     *
     *
     *     Order of insertion in a hashMap
     *      Time complexity()
     *
     *                                              |-35, qwerty--|
     *     |-50, norm--|                            |-25, Abcd--|
     *     [---0---|---1----|--2---|--3----|--4----|-5--|-6--|--7------|--8-----|-9--------]
     */



    public static void main(String[] args) {
        UserInfo chandler = new UserInfo();
        chandler.name = "Chandler";
        UserInfo joey = new UserInfo();
        joey.name = "Joey";
        System.out.println(joey.equals(chandler));
        Map<UserInfo, Integer> userInfoToFrequencyMap = new HashMap<>();
        userInfoToFrequencyMap.put(joey, 1);
        userInfoToFrequencyMap.put(chandler, 2);
        System.out.println(userInfoToFrequencyMap);
    }

}

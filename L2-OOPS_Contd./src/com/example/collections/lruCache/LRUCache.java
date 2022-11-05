package com.example.collections.lruCache;

public class LRUCache {
    /**
     *
     *  Map
     *      -- How hashMap behaves
     *
     *      --> LinkedList
     *
     *
     *          --> LinkedHashMap
     *          extra functionality (it preserves order of insertions)
     *          Implement it
     *          (HashMap)    + linkedList
     *
     *          {10, "sachin"}, {20 , "sky"} , 30, "kohli", 5, "bumrah"  , 10 ,"hardik"
     *          modulo 10
     *
     *                                  <5, bumrah></>
     *          Buckets<0 , 1, 2, 3, 4, 5, 6, 7, 8, 9>
     *                  <10, hardik>
     *                  <20, sky>
     *                  <30, kohli>
     *
     *          LinkedList<20, 30, 5, 10>
     *
     *
     *    --> TreeMap
     *    <keys are always in sorted order>
     *
     *
     *
     *
     */
}

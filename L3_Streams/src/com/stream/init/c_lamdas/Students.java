package com.stream.init.c_lamdas;

public class Students {
    Double rollNo;

    public Students() {
        this.rollNo = Math.random();
    }

    public static int compareById(Students o1 , Students o2){
        return o1.rollNo.compareTo(o2.rollNo);
    }
}

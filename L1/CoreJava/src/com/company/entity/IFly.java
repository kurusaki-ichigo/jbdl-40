package com.company.entity;

public interface IFly {
    default void print(){
        System.out.println("Fly");
    }


    static void printHello(){
        System.out.println("hello world");
    }
}

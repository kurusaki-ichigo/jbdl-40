package com.employee;

public interface IInterfaceExample {

    /**
     * this came into being
     * < 8 but 8 and above
     *
     */
    public default void doSomething(){
        System.out.println("hello world");
    }

    void print();

}

package com.stream.init;

import com.stream.init.a_interface_instance.IDemoInterface;

public class Main {


    /**
     *
     *  Can we create instance of Interface
     *      --> Yes (1)
     *
     *      --> No
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        /**
         * anonymous inner class
         */
        IDemoInterface ostrich = new IDemoInterface() {
            @Override
            public boolean fly() {
                return false;
            }
        };
        IDemoInterface pigeon = new IDemoInterface() {
            @Override
            public boolean fly() {
                return true;
            }

        };

        /**
         *
         *  Define a lambda
         *
         *      a lamda expression
         *  is used to provide the implementation of the interface (necessarily functional interface)
         */
        IDemoInterface bird = () -> true;

        /**
         *  whats basically a lamda
         *
         *  (args) --> {body associated}
         *
         */



    }
}

package com.multi.threading.interview.oddeven;

public class Printer {

    /**
     * one way
     */
   volatile int a ;
    int max;

    /**
     *
     * volatile keyword
     * @param a
     * @param max
     */

    Printer(int a , int max){
        this.a = a;
        this.max = max;
    }

    /**
     *
     *  What is a singleton design pattern -- (next section )
     *
     */

    void  printEven() {
        while (a < max){
            synchronized (this){
                if(a % 2 == 0){
                    a++;
                    System.out.println(a);
                    this.notify();
//                    System.out.println(" notified by  " +Thread.currentThread().getName());
                } else {
                    try {
//                        System.out.println(" waiting for  " +Thread.currentThread().getName());
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void printEvenOld(){
        while (a < max) {
            if(a % 2 == 0) {
                a++;
                System.out.println(a);
            }
        }
    }


    void printOddOld(){
        while (a < max) {
            if(a%2 == 1){
                a++;
                System.out.println(a);
            }
        }
    }

    void printOdd(){
        while (a < max){
            synchronized (this){
                if(a%2 == 1){
                    a++;
                    System.out.println(a);
                    this.notify();
//                    System.out.println(" notified by  " +Thread.currentThread().getName());
                } else {
                    try {
//                        System.out.println(" waiting for  " +Thread.currentThread().getName());
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}

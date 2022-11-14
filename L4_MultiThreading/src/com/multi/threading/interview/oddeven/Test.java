package com.multi.threading.interview.oddeven;

public class Test {

    public static void main(String[] args) {
        Printer p = new Printer(0, 1000);
        Thread odd = new Thread(new OddRunner(p));
        Thread even = new Thread(new EvenRunner(p));
        even.start();
        odd.start();
    }
}

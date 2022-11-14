package com.multi.threading.interview.oddeven;

public class EvenRunner implements Runnable{
    Printer printer;

    EvenRunner(Printer printer){
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.printEvenOld();
    }
}

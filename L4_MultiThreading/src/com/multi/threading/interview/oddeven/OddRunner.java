package com.multi.threading.interview.oddeven;

public class OddRunner implements Runnable{
    Printer printer;

    OddRunner(Printer printer){
        this.printer = printer;
    }
    @Override
    public void run() {
        printer.printOddOld();
    }
}

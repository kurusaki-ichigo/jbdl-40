package com.multi.threading;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ParallelProcessing {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());


        List<Integer> fetchPrime = Arrays.asList(100000, 10003, 83294, 13184, 31293, 12121, 102341);
//        List<Integer> fetchPrime = Arrays.asList(10);
        /**
         * Different ways to create a thread
         *      Best practices followed
         *          - > Runnable interface
         *              --> single method and you can easily achieve lambda expression and inject over stream processing
         *
         * Implementing Runnable Interface
         *
         * Extending thread class
         */
        /**
         * anonymous inner class
         */
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        /**
         * lambda expression
         */
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));

        /**
         * various ways to create a threadpool and submit tasks
         * Executor service
         *
         * 1 processor for main thread
         *  and rest other processors cann be utilized by other threadds
         *
         *
         *  - cases
         *      L 64 will be printed at the end --
         *      L 64 will be printed at the start
         *      L 64 will be printed somewhere in between
         *      Cant Be determined -
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */
        Thread[] customThreadPool = new Thread[Runtime.getRuntime().availableProcessors() -1];
        LocalDateTime start = LocalDateTime.now();
        IntStream.range(0, fetchPrime.size()).forEach(inp -> {
            DemoThread demoThread = new DemoThread(fetchPrime.get(inp));
            customThreadPool[inp] = new Thread(demoThread);
            customThreadPool[inp].start();
        });
        LocalDateTime end = LocalDateTime.now();

        /**
         * join is an block call
         *  and our program will not move ahead until the thread on which it is called completes
         */
        IntStream.range(0, fetchPrime.size()).forEach(inp -> {
            try {
                System.out.println(" waiting for " + customThreadPool[inp].getName() + " to finish");
                customThreadPool[inp].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        Duration between = Duration.between(start, end);
        System.out.println( Thread.currentThread().getName() + "  Time taken " + between.getNano());


        ExecutorService executors =  Executors.newFixedThreadPool(10);
        LocalDateTime startExecutor = LocalDateTime.now();
        List<Future<?>> callBackResponse = new ArrayList<>();
        IntStream.range(0, fetchPrime.size()).forEach(inp -> {
            callBackResponse.add(executors.submit(() -> {
                System.out.println(" Executor Processing :: start by " + Thread.currentThread().getName());
                List<Integer> integers = SequentialProcessing.firstNPrimeNumbers(fetchPrime.get(inp));
                System.out.println(" Executor Processing :: end by " + Thread.currentThread().getName());
            }));
        });

        /**
         * Future.get() is an block call
         *  and our program will not move ahead until the thread on which it is called completes
         */
        callBackResponse.forEach(response -> {
            try {
                response.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executors.shutdown();
        LocalDateTime endExecutor = LocalDateTime.now();
        Duration betweenExecutor = Duration.between(startExecutor, endExecutor);
        System.out.println( Thread.currentThread().getName() + "  Time taken " + betweenExecutor.getNano());

    }


    static class DemoThread implements Runnable {
        int prime;
        DemoThread(int primes){
            this.prime = primes;
        }

        @Override
        public void run() {
            System.out.println(" Processing :: start by " + Thread.currentThread().getName());
                List<Integer> integers = SequentialProcessing.firstNPrimeNumbers(prime);
            System.out.println(" Processing :: end by " + Thread.currentThread().getName());
        }
    }

    // 2757000
    // 455496000

}

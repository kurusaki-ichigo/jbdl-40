package com.multi.threading;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SequentialProcessing {

    public static void main(String[] args) {

        List<Integer> fetchPrimes = Arrays.asList(100000, 10003, 83294, 13184, 31293, 12121, 102341, 122489);
//        List<Integer> fetchPrimes = Arrays.asList(10);
        LocalDateTime start = LocalDateTime.now();
        List<List<Integer>> collect = fetchPrimes.stream().map(SequentialProcessing::firstNPrimeNumbers).collect(Collectors.toList());

        System.out.println(collect);
        LocalDateTime end = LocalDateTime.now();
        System.out.println(" time taken "+ Duration.between(start, end).getNano());

    }

   static List<Integer> firstNPrimeNumbers(int n){
//       System.out.println(" fetching prime :: " + n);
        List<Integer> output = new ArrayList<>();
        int count = 0;
        int result = 2;
        while (count <= n) {
            if (isPrime(result)) {
//                System.out.println(" result :: is prime :: "+ result);
                output.add(result);
                count++;
            }
            result++;
        }
        return output;
    }


    static boolean isPrime(int input){
        if(input <= 1){
            return false;
        }
        if(input == 2 || input == 3){
            return true;
        }

        for(int i = 2; i <= Math.sqrt(input); i++){
            if(input % i ==0){
                return false;
            }
        }
        return true;
    }

}

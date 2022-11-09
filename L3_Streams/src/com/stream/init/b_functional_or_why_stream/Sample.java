package com.stream.init.b_functional_or_why_stream;

import java.util.*;
import java.util.stream.Collectors;

public class Sample {

    public static void main(String[] args) {
        /**
         *  List<10> and we want to sort them in decreasing order
         *
         */

        List<Integer> inputList = Arrays.asList(12, 16, 343, 18, 53, 2, -1 , 0, 5, 19);

//        /**
//         * Decreasing order
//         *      - Comparator
//         *      ----> Comparator vs Comparable
//         */
//
//        Collections.sort(inputList, Collections.reverseOrder());
//
//        Collections.sort(inputList, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
//
//        /**
//         *
//         * (args) -> body
//         *
//         */
//
//        Collections.sort(inputList , (o1, o2) -> o1.compareTo(o2));


        /**
         *
         * find the first 2 odd numbers and return the cube of them
         */
        // fetch the 2 odd numbers
        int count = 0;
        List<Integer> oddList = new ArrayList<>();
        for(Integer input : inputList){
            if(input % 2 == 1){
                oddList.add(input);
                count++;
            }

            if(count >= 2){
                break;
            }
        }

        List<Integer> cubeList = new ArrayList<>();
        for(Integer inp : oddList){
            cubeList.add(inp*inp*inp);
        }
        System.out.println(cubeList);

        List<Integer> collect = inputList.stream()
                .filter(inp -> inp % 2 == 1)
                .map(inp -> inp * inp * inp)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(collect);


    }
}

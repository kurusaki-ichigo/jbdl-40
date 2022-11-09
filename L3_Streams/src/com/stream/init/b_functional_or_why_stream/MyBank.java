package com.stream.init.b_functional_or_why_stream;

import java.util.*;
import java.util.stream.Collectors;

public class MyBank {


    /**
     * find all the transactions of either the type SHOPPING or MOVIES
     * and return the transactionIds in decreasing order of the value
     *
     * @param args
     */
    public static void main(String[] args) {

        List<Transactions> myBankTransactions = Arrays.asList(
                new Transactions(500, TransactionType.SHOPPING),
                new Transactions(600, TransactionType.DONATIONS),
                new Transactions(700, TransactionType.MOVIES),
                new Transactions(800, TransactionType.CONVEYANCE),
                new Transactions(900, TransactionType.MISC),
                new Transactions(1000, TransactionType.MOVIES),
                new Transactions(1100, TransactionType.CASHBACK),
                new Transactions(1200, TransactionType.CASHBACK)
        );

        List<Transactions> shoppingOrMoviesTransactions = new ArrayList<>();
        for(Transactions transaction : myBankTransactions){
            if(transaction.getTransactionType() == TransactionType.SHOPPING || transaction.getTransactionType() == TransactionType.MOVIES){
                shoppingOrMoviesTransactions.add(transaction);
            }
        }

        // sort the transactions in decreasing order of value
        Collections.sort(shoppingOrMoviesTransactions, new Comparator<Transactions>() {
            @Override
            public int compare(Transactions o1, Transactions o2) {
                return o2.getAmount().compareTo(o1.getAmount());
            }
        });

        List<Double> transactionids = new ArrayList<>();
        for(Transactions transaction : shoppingOrMoviesTransactions){
            transactionids.add(transaction.getId());
        }

        System.out.println(transactionids);
        // around 20 lines

        List<Double> collect = myBankTransactions.stream()
                .filter(myTransaction -> myTransaction.getTransactionType() == TransactionType.SHOPPING || myTransaction.getTransactionType() == TransactionType.MOVIES)
                .sorted(Comparator.comparing(Transactions::getAmount).reversed())
                .map(Transactions::getId)
                .collect(Collectors.toList());

        /**         (o1, o2) --> o1.amoutn.compareto(o2.amount).reversed
         *                          --> Predicate       --> Comparator      --> Function       --> reducing operation
         *  List<Transactions       --> filter          --> sort            --> map             --> collect
         *
         *
         * Stream
         *   a sequence of objects that supports variable methods which can be pipelined to produce the desired result
         *
         *   - it is not a DS
         *      instead what it does is take input from various collections, arrays , I/O chanels
         *      and they do not change the original DS.
         *
         */

        System.out.println(collect);
        // around 6 lines of code

    }




}

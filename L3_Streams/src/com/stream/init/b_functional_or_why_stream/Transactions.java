package com.stream.init.b_functional_or_why_stream;

public class Transactions {
    public Double getId() {
        return id;
    }

    Integer amount;
    TransactionType transactionType;
    Double id;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Transactions(Integer amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.id = Math.random();
    }
}

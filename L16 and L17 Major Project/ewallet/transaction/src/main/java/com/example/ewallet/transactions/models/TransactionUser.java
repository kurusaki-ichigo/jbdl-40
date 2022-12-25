package com.example.ewallet.transactions.models;

import com.example.ewallet.transactions.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionUser {

    UUID fromWalletId;
    UUID fromUserId;
    UUID toWalletId;
    UUID toUserId;
    Double amount;


    public Transaction toTransaction(){
        return Transaction.builder()
                .fromWalletId(fromWalletId)
                .toWalletId(toWalletId)
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .amount(amount).build();
    }

}

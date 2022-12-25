package com.example.ewallet.transactions.models;

import com.example.ewallet.transactions.entity.TransactionStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class TransactionInfo {

    UUID transactionId;
    UUID fromWalletId;
    UUID toWalletId;
    UUID fromUserId;
    UUID toUserId;
    Double amount;
    TransactionStatus transactionStatus;
}

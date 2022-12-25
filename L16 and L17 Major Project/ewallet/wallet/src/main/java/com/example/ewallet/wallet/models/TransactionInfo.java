package com.example.ewallet.wallet.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfo {

    UUID transactionId;
    UUID fromWalletId;
    UUID toWalletId;
    UUID fromUserId;
    UUID toUserId;
    Double amount;
    Status transactionStatus;

}

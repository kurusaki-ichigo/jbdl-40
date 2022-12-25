package com.example.ewallet.notification.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletInfo {

    UUID fromWalletId;
    UUID fromUserId;

    UUID toWalletId;
    UUID toUserId;

    Double amount;


}

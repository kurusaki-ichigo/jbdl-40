package com.example.ewallet.wallet.models;

import com.example.ewallet.wallet.entity.Wallet;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletUser {

    private UUID userId;
    private String name;
    private String email;



    public Wallet associatedWallet(){
        return Wallet.builder()
                .userId(userId)
                .balance(0.0)
                .build();
    }



}

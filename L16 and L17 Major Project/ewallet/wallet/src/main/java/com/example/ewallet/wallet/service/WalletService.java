package com.example.ewallet.wallet.service;

import com.example.ewallet.wallet.entity.Wallet;
import com.example.ewallet.wallet.exception.InsufficientFundsException;
import com.example.ewallet.wallet.exception.WalletNotFoundException;
import com.example.ewallet.wallet.models.TransactionInfo;
import com.example.ewallet.wallet.models.WalletUser;
import com.example.ewallet.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
public class WalletService {


    @Autowired
    WalletRepository walletRepository;

    @Value("${system.wallet.id}")
    UUID systemWalletId;


    public Wallet createNewWallet(WalletUser walletUser){
        Wallet wallet = walletUser.associatedWallet();
        return walletRepository.save(wallet);
    }


    @Transactional(rollbackFor = Exception.class)
    public void performTransaction(TransactionInfo transactionInfo){
        UUID senderWalletId = transactionInfo.getFromWalletId();
        UUID receivedWalletId = transactionInfo.getToWalletId();
        if(!Objects.equals(systemWalletId, senderWalletId)){
            Wallet senderWallet = walletRepository.findById(senderWalletId).orElseThrow(WalletNotFoundException::new);

            Double balance = senderWallet.getBalance();
            Double effective = balance - transactionInfo.getAmount();
            if(effective < 0){
                    throw new InsufficientFundsException();
            }
            senderWallet.setBalance(effective);
            saveOrUpdate(senderWallet);
        }


        if(!Objects.equals(systemWalletId, receivedWalletId)){
            Wallet receiver = walletRepository.findById(receivedWalletId).orElseThrow(WalletNotFoundException::new);
            Double balance = receiver.getBalance();
            Double effective = Double.sum(balance,  transactionInfo.getAmount() != null ?  transactionInfo.getAmount() : 0.0);
            receiver.setBalance(effective);
            saveOrUpdate(receiver);
        }
    }

    public Wallet saveOrUpdate(Wallet wallet){
        return walletRepository.save(wallet);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }


}

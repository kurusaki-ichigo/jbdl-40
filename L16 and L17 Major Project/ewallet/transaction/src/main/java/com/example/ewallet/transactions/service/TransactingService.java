package com.example.ewallet.transactions.service;

import com.example.ewallet.transactions.entity.Transaction;
import com.example.ewallet.transactions.entity.TransactionStatus;
import com.example.ewallet.transactions.exception.TransactionNotFoundException;
import com.example.ewallet.transactions.models.TransactionInfo;
import com.example.ewallet.transactions.models.TransactionUser;
import com.example.ewallet.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactingService {

    @Autowired
    TransactionRepository repository;


    public Transaction createNewTransaction(TransactionUser transactionUser){
        Transaction transaction = transactionUser.toTransaction();
        return saveOrUpdate(transaction);
    }


    public Transaction updateRecord(TransactionInfo transactionInfo, TransactionStatus transactionStatus){
        Transaction existingTransaction = repository.findById(transactionInfo.getTransactionId())
                .orElseThrow(TransactionNotFoundException::new);

        existingTransaction.setTransactionStatus(transactionStatus);
        return saveOrUpdate(existingTransaction);
    }


    public Transaction saveOrUpdate(Transaction transaction){
        return repository.save(transaction);
    }

}

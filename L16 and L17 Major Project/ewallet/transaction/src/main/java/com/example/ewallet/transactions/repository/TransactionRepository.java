package com.example.ewallet.transactions.repository;

import com.example.ewallet.transactions.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

/**
 * last 30 days transactions
 */

    /**
     * is this the good way -- ?
     * @param toWalledId
     * @param start
     * @param end
     * @return
     */
    List<Transaction> findAllByToWalletIdAndCreatedAtBetween(UUID toWalledId , OffsetDateTime start, OffsetDateTime end);

    /**
     * Paginated api
     */
    Page<Transaction> findAllByToWalletIdAndCreatedAtBetween(UUID toWalledId , OffsetDateTime start, OffsetDateTime end,
                                                             PageRequest pageRequest);


}

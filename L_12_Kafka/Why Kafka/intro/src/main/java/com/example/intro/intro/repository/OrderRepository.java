package com.example.intro.intro.repository;

import com.example.intro.intro.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {
}

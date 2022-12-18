package com.example.new_order.new_order.repository;


import com.example.new_order.new_order.entity.JWTClient;
import com.example.new_order.new_order.entity.S2SClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JWTClientRepository extends JpaRepository<JWTClient, Long> {

    Optional<JWTClient> findByS2SClient(S2SClient s2SClient);
}

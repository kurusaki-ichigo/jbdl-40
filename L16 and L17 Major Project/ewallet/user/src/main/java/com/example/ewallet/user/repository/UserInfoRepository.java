package com.example.ewallet.user.repository;

import com.example.ewallet.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {


    Optional<UserInfo> findByEmail(String emailId);

}

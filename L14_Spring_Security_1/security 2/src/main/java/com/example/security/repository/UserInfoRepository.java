package com.example.security.repository;

import com.example.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


    Optional<UserInfo> findUserInfoByEmail(String email);

}

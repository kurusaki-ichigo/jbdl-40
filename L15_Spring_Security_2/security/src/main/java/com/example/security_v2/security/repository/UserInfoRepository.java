package com.example.security_v2.security.repository;

import com.example.security_v2.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


    Optional<UserInfo> findUserInfoByEmail(String email);

}

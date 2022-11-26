package com.example.l7.respository;

import com.example.l7.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByEmail(String email);


//    @Query("select user from UserEntity user where user.email = :fruit")
//    Optional<UserEntity> fetchByEmailId(@Param("fruit") String email);
//
//
//    @Query(value = " select * from user where email = :email", nativeQuery = true)
//    Optional<UserEntity> fetchingUserByEmail(@Param("email") String email);


}

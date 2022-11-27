package com.example.L8.service;

import com.example.L8.entities.User;
import com.example.L8.exception.DuplicateUserException;
import com.example.L8.models.StatusCodes;
import com.example.L8.repository.UserRepository;
import com.example.L8.requests.CreateNewUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.L8.models.StatusCodes.DUPLICATE_USER;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User createNewUser(CreateNewUserRequest request) {
        User user = request.toUser();
        Optional<User> existingUser = findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new DuplicateUserException(DUPLICATE_USER);
        }
        return saveOrUpdate(user);
    }

    public Optional<User> findById(String id){
        UUID uuid = UUID.fromString(id);
        return repository.findById(uuid);
    }


    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

    public User saveOrUpdate(User user){
        return repository.save(user);
    }



}

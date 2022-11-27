package com.example.L8.service;

import com.example.L8.entities.Authors;
import com.example.L8.repository.AuthorRepository;
import com.example.L8.requests.CreateNewBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthorService {


    @Autowired
    AuthorRepository authorRepository;


    /**
     *
     *
     *
     *
     *
     author ---> check if author exists (same email)
     *                                      --> if exists (return author)
     *                                              -- else create new author
     *
     *
     * * @param request
     * @return
     */
    public Authors createNewAuthor(CreateNewBookRequest request){
        Authors authors = request.toAuthor();
        Optional<Authors> existingAuthor = findByEmail(authors.getEmail());
        return existingAuthor.orElseGet(() -> saveOrUpdate(authors));
    }

    public Optional<Authors> findByEmail(String email){
        return authorRepository.findByEmail(email);
    }

    public Authors saveOrUpdate(Authors authors){
        return authorRepository.save(authors);
    }

}

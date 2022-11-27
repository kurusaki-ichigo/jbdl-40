package com.example.L8.requests;

import com.example.L8.entities.Authors;
import com.example.L8.entities.Books;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateNewBookRequest {

    @NotNull
    String bookName;
    @NotNull
    String bookIsbn;
    @Positive
    Double bookAmount;

    @NotNull
    String authorName;
    @Email
    String authorEmail;


    public Authors toAuthor(){
        return Authors.builder()
                .name(authorName)
                .email(authorEmail).build();
    }

    public Books toBook(){
        return Books.builder()
                .name(bookName)
                .isbn(bookIsbn)
                .purchaseAmount(bookAmount)
                .authors(toAuthor()).build();
    }

}

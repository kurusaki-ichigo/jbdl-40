package com.example.cache.cache.request;

import com.example.cache.cache.entity.Books;
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




    public Books toBook(){
        return Books.builder()
                .name(bookName)
                .isbn(bookIsbn)
                .purchaseAmount(bookAmount)
                .build();
    }

}

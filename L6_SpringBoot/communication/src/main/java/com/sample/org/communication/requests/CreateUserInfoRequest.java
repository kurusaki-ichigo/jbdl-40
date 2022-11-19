package com.sample.org.communication.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserInfoRequest {

    @NotEmpty
    String name;
    @Email
    String email;
    String gender;


}

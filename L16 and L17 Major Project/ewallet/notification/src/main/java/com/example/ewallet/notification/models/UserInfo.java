package com.example.ewallet.notification.models;

import lombok.Data;

import java.util.UUID;

@Data
public class UserInfo {

    UserDetails data;


    @Data
  public static class UserDetails {
      private UUID userId;
      private String name;
      private String email;
  }

}

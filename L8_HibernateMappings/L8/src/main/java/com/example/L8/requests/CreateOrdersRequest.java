package com.example.L8.requests;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrdersRequest {

    List<String> bookIds;

    String userId;
}

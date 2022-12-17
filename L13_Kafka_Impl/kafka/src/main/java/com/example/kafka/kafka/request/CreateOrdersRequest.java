package com.example.kafka.kafka.request;

import com.example.kafka.kafka.entity.Orders;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateOrdersRequest {


    String source;
    String destination;
    PaxInfo paxInfo;
    String doj;

    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PaxInfo implements Serializable {
        public FoodPreference foodPreference;
        public String email;
        public String name;
        public UUID userId;
    }


    public Orders toOrders(){
        return Orders.builder()
                .source(source)
                .destination(destination)
                .doj(doj)
                .build();
    }

}

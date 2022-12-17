package com.example.intro.intro.request;

import com.example.intro.intro.entity.Orders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateOrdersRequest {

    /**
     {
     *                "Source"
     *                "Destination"
     *                "DOJ"
     *                PAXINFO {
     *                      ""
     *                      user_preferences {
     *
     *                      }
     *                }
     *                user_id:
     *            }
     *     */
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

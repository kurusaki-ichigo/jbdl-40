package com.example.L8.utils;

import com.example.L8.models.StatusCodes;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.findAndRegisterModules();
    }


    /**
     *
     *      Payment for order
     *          (ZOMATO / Food Delivery / AMAZON food / Deliveroo / Uber Eats)
     *
     *
     *          Paying for order
     *
     *           1)     User (User Microservice)
     *           2)     Payment (Payment Microservice) -- deducting money from bank
     *           3)     Order Service
     *          4)      Notification
     *
     *
     *
     *
     *
     *      How do services communicate between each other
     *              --> OkHttp
     *              --> Rest Template ---> acquiers TCP connection over the external service socket
     *
     *
     *
     *          Payment for an order -----> /payment microservice ---?
     *
     *          One of the implementation with spring boot
     *
     *          FE / APP---> How the request flow
     *                                  -----> Api Gateway ---> rate limitter and throttler but also for end locations / akamai
     *                                                      --------> Load Balancer
     *                                                                          ----------> one of the instance
     *
     *
     *
     *
     *         NETFLIX OSS
     *                          Eureka  -- serverice registry (it would have routes for all the api end points)
     *
     *
     *
     *                          Zuul    -- aggregator (would redirect your request to the exact microservice)
     *
     *              https://www.baeldung.com/zuul-load-balancing
     *                                                                                  Eureka (interal.zomato.com/payment)
     *                                                                                  Eureka (interal.zomato.com/order)
     *                                                                   _ _            Eureka (interal.zomato.com/user)
     *                                                                  /
     *                      Ribbon -- load balancer     -----------> Zuul (hey call payment zomato.com/payment , paylad {})
     *                                                                  \
     *                                                                     \
     *                                                                      \
     *                                                                      \
     *                                                                          (Payment, paylad{} )
     *                                                                                  --> credit card
     *                                                                                      ----> fetch user profile
     *                                                                              \
     *                                                                               \_____(spanId)__
     *                                                                                      (User Microservice)
     *                                                                               <----XXXXX-- (credit card details)
     *
     *
     *                                                                               |
     *                                                                               |
     *                                                                               |______
     *                                                                                      (Order Service)
     *                                                                                      mark order success
     *
     *
     *                                                                                |
     *                                                                                |
     *                                                                                |
     *                                                                                  ----- Notification Service
     *                                                                                      (send email to user, your order is success)
     *
     *
     *       TraceId
     *          ---> enable it ?
     *
     *        SpanID
     *
     *        TraceId ----> Unique requestId
     *          SpanId ---> per microserice unique
     *          Dependency
     *              --> Spring Sleuth
     *
     *
     *
     * @param data
     * @param status
     * @param <T>
     * @return
     */
    @SneakyThrows
    public <T> ResponseEntity<String> generateResponse(T data, HttpStatus status){
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setData(data);
        responseInfo.setTraceId(MDC.get("traceId"));
        return new ResponseEntity<>(mapper.writeValueAsString(responseInfo), status);
    }

    @SneakyThrows
    public ResponseEntity<String> generateExceptionResponse(StatusCodes codes){
        ResponseInfo<String> responseInfo = new ResponseInfo<>();
        responseInfo.setErrorCode(codes.getCode());
        responseInfo.setErrorMessage(codes.getMessage());
        responseInfo.setTraceId(MDC.get("traceId"));
        return new ResponseEntity<>(mapper.writeValueAsString(responseInfo), codes.getStatus());
    }



}

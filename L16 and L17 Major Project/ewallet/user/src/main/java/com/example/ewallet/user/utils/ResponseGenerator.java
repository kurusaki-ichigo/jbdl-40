package com.example.ewallet.user.utils;

import com.example.ewallet.user.models.StatusCode;
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

    @SneakyThrows
    public <T> ResponseEntity<String> generateResponse(T data, HttpStatus status){
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setData(data);
        responseInfo.setTraceId(MDC.get("traceId"));
        return new ResponseEntity<>(mapper.writeValueAsString(responseInfo), status);
    }

    @SneakyThrows
    public ResponseEntity<String> generateExceptionResponse(StatusCode codes){
        ResponseInfo<String> responseInfo = new ResponseInfo<>();
        responseInfo.setErrorCode(codes.getCode());
        responseInfo.setErrorMessage(codes.getMessage());
        responseInfo.setTraceId(MDC.get("traceId"));
        return new ResponseEntity<>(mapper.writeValueAsString(responseInfo), codes.getStatus());
    }



}

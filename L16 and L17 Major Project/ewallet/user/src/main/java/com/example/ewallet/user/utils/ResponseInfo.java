package com.example.ewallet.user.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo<T> {

    T data;
    /**
     * Covered in the next session
     */
    String traceId;
    String errorCode;
    String errorMessage;






}

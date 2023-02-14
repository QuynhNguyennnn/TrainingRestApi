package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.demo.api.errors.ApiError;

/**
 * Api Exception.
 * 
 * @author QuynhNN
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class APIException extends RuntimeException {
    private String code;
    private String message;

    private HttpStatus statusCode;
    private ApiError apiError;
    
    public APIException(String code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public APIException(ApiError apiError, HttpStatus statusCode) {
        this.apiError = apiError;
        this.statusCode = statusCode;
    }
}

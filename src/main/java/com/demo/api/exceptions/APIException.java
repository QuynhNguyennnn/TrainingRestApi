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
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {
    private String code;
    private String message;
    private HttpStatus statusCode;
    private ApiError apiError;

    /**
     * Construtor.
     * 
     * @param code       code of error
     * @param message    message for error
     * @param statusCode error status code
     */
    public ApiException(String code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    /**
     * Another constructor.
     * 
     * @param apiError   apiError to save code and message
     * @param statusCode error status code
     */
    public ApiException(ApiError apiError, HttpStatus statusCode) {
        this.apiError = apiError;
        this.statusCode = statusCode;
    }
}

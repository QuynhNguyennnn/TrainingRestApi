package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.demo.api.errors.ApiError;

/**
 * Bad Request Exception.
 * 
 * @author QuynhNN
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApiException {

    /**
     * Constructor.
     * 
     * @param apiError apiError to save code and message
     */
    public BadRequestException(ApiError apiError){
        super(apiError, HttpStatus.BAD_REQUEST);
    }
}

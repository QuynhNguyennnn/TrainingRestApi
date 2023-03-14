package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.demo.api.errors.ApiError;

/**
 * Conflict Exception.
 * 
 * @author QuynhNN
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictException extends ApiException{
    
    /**
     * Constructor.
     * 
     * @param apiError apiError to save code and message
     */
    public ConflictException(ApiError apiError){
        super(apiError, HttpStatus.CONFLICT);
    }
}

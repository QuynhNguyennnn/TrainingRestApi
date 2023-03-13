package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.demo.api.errors.ApiError;

/**
 * Id Not Found Exception.
 * 
 * @author QuynhNN
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IdNotFoundException extends ApiException {

    /**
     * Constructor. 
     * 
     * @param apiError
     */
    public IdNotFoundException(ApiError apiError){
        super(apiError, HttpStatus.NOT_FOUND);
    }
}

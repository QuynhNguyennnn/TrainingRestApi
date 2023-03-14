package com.demo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Http Exception Handle.
 * 
 * @author QuynhNN
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HttpExceptionHandle extends ApiException {
    
    /**
     * Constructor.
     * 
     * @param code code of error
     * @param message message of error
     */
    public HttpExceptionHandle(String code, String message) {
        super(code, message, HttpStatus.BAD_REQUEST);
    }
}
